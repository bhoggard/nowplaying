(ns nowplaying.models.feed
  (:require [clojure.data.json :as json]
            [clojure.xml :as xml]))

(def q2-url "http://www.wqxr.org/api/whats_on/q2/2/")
(def counterstream-url "http://www.live365.com/pls/front?handler=playlist&cmd=view&viewType=xml&handle=amcenter&maxEntries=1")
(def yle-url "http://yle.fi/radiomanint/LiveXML/r17/item(0).xml")

(defn get-json
  [response]
  (json/read-str response))

(defn wrap-feed-errors
  "wrap outside http calls so we can trap them"
  [f]
  (try
    (f)
    (catch Exception e (hash-map :title "" :composer ""))))

(defn translate-q2
  "translate parsed JSON into title and composer for Q2 Music"
  [data]
  (let [entry ((data "current_playlist_item") "catalog_entry")
        title (entry "title")
        composer ((entry "composer") "name")]
    (hash-map :title title :composer composer)))

(defn q2-raw
  "get feed for Q2 Music"
  []
  (translate-q2 (get-json (slurp q2-url))))

(defn q2 [] (wrap-feed-errors q2-raw))

(defn translate-counterstream
  "translate parsed XML into title and composer for Counterstream Radio"
  [data]
  (let [entry (-> data :content (get 2))
        title (-> entry :content (get 0) :content first)
        composer (-> entry :content (get 1) :content first)]
    (hash-map :title title :composer composer)))

(defn counterstream-raw
  "get feed for Counterstream Radio"
  []
  (-> counterstream-url xml/parse translate-counterstream))

(defn counterstream [] (wrap-feed-errors counterstream-raw))

(defn translate-yle
  "translate parsed XML into title and composer for YLE Klassinen"
  [data]
  (let [entry (-> (xml/parse yle-url) :content first :attrs)
        title (-> entry :TITLE)
        composer (-> entry :COMPOSER)]
        (hash-map :title title :composer composer)))

(defn yle-raw
  "get feed for YLE Klassinen"
  []
  (-> yle-url xml/parse translate-yle))

(defn yle [] (wrap-feed-errors yle-raw))
