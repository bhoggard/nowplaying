(ns nowplaying.routes.home
  (:require [compojure.core :refer :all]
            [net.cgrand.enlive-html :as html]))

(defn q2-feed
  "get feed for q2 music"
  []
  {
    :title "Rodeo Suite"
    :componser "Aaron Copeland"
  })

(html/deftemplate index-page "index.html"
  [q2-data]
  [:#q2 :p.title] (html/content (:title q2-data)))

(defn home []
  (index-page (q2-feed)))

(defroutes home-routes
  (GET "/" [] (home)))
