(ns ^:figwheel-hooks cljs-asciidoctor.core
  (:require
   [reagent.dom :refer [render]]
   [re-frame.core :as re-frame]
   [cljs-asciidoctor.use-cases.core-cases :as ccases]
   [cljs-asciidoctor.routes :as routes]
   [cljs-asciidoctor.views.home :as views]
   [cljs-asciidoctor.config :as config]
   [cljs-asciidoctor.styles :as styl]))



(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (println "mount")
  (re-frame/clear-subscription-cache!)
  (styl/inject-trace-styles js/document)
  (render [views/main-panel]
          (.getElementById js/document "app")))

(defn ^:after-load re-render []
  (mount-root))

(defn ^:export init []
  (println "init again..")
  (re-frame/dispatch-sync [::ccases/initialize-db])
  (dev-setup)
  (routes/app-routes)

  (mount-root))

(defonce init-block (init))
