(ns nowplaying.models.feed
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def q2-url "http://www.wqxr.org/api/whats_on/q2/2/")

(defn http-call
  [url]
  (client/get url))

(defn get-json
  [response]
  (json/read-str (:body response)))

(defn q2
  "get feed for q2 music"
  []
  (let [data (get-json (http-call q2-url))
        entry ((data "current_playlist_item") "catalog_entry")
        title (entry "title")
        composer ((entry "composer") "name")]
    (hash-map :title title :composer composer)))
