(ns nowplaying.routes.home
  (:require [compojure.core :refer :all]
            [net.cgrand.enlive-html :as html]))

(html/deftemplate index-page "index.html" [])

(defn home []
  (index-page))

(defroutes home-routes
  (GET "/" [] (home)))
