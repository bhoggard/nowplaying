(ns nowplaying.models.feed
  (:require [clojure.data.json :as json]
            [clojure.xml :as xml]))

(def q2-url "http://www.wqxr.org/api/whats_on/q2/2/")
(def counterstream-url "http://www.live365.com/pls/front?handler=playlist&cmd=view&viewType=xml&handle=amcenter&maxEntries=1")

(defn get-json
  [response]
  (json/read-str response))

(defn translate-q2
  "translate parsed JSON into title and composer"
  [data]
  (let [entry ((data "current_playlist_item") "catalog_entry")
        title (entry "title")
        composer ((entry "composer") "name")]
    (hash-map :title title :composer composer)))

(defn q2
  "get feed for q2 music"
  []
  (translate-q2 (get-json (slurp q2-url))))

(defn translate-counterstream
  "translate parsed XML into title and composer"
  [data]
  (let [entry (-> data :content (get 2))
        title (-> entry :content (get 0) :content first)
        composer (-> entry :content (get 1) :content first)]
    (hash-map :title title :composer composer)))

(defn counterstream
  "get feed for counterstream radio"
  []
  (-> counterstream-url xml/parse translate-counterstream))