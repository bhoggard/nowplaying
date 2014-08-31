(defproject nowplaying "0.1.0-SNAPSHOT"
  :description "one-page clojure application to show what's playing on my favorite classical stations"
  :url "http://nowplaying.tristanmedia.com"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/mit-license.php"
            :distribution :repo}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [enlive "1.1.5"]
                 [ring-server "0.3.1"]
                 [clj-http "1.0.0"]
                 [org.clojure/data.json "0.2.5"]
                 [org.clojure/data.zip "0.1.1"]
                 [ring/ring-jetty-adapter "1.3.0"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler nowplaying.handler/app
         :init nowplaying.handler/init
         :destroy nowplaying.handler/destroy}
  :uberjar-name "nowplaying-standalone.jar"
  :main ^:skip-aot nowplaying.handler/main
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.1"]]}})
