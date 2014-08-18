(ns nowplaying.routes.home
  (:require [compojure.core :refer :all]
            [net.cgrand.enlive-html :as html]
            [nowplaying.models.feed :as feed]))

(html/deftemplate index-page "index.html"
  [q2-data]
  [:#q2 :p.title] (html/content (:title q2-data))
  [:#q2 :p.composer] (html/content (:composer q2-data)))

(defn home []
  (index-page (feed/q2)))

(defroutes home-routes
  (GET "/" [] (home)))
