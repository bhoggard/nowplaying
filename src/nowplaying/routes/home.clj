(ns nowplaying.routes.home
  (:require [compojure.core :refer :all]
            [net.cgrand.enlive-html :as html]
            [nowplaying.models.feed :as feed]))

(html/deftemplate index-page "index.html"
  [q2-data counterstream-data yle-data second-inversion-data]
  [:#q2 :p.title] (html/content (:title q2-data))
  [:#q2 :p.composer] (html/content (:composer q2-data))
  [:#counterstream :p.title] (html/content (:title counterstream-data))
  [:#counterstream :p.composer] (html/content (:composer counterstream-data))
  [:#yle :p.title] (html/content (:title yle-data))
  [:#yle :p.composer] (html/content (:composer yle-data))
  [:#second-inversion :p.title] (html/content (:title second-inversion-data))
  [:#second-inversion :p.composer] (html/content (:composer second-inversion-data)))

(defn home []
  (index-page (feed/q2) (feed/counterstream) (feed/yle) (feed/second-inversion)))

(defroutes home-routes
  (GET "/" [] (home)))
