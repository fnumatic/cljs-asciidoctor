(ns cljs-asciidoctor.routes
  (:require
    [re-frame.core :as rf]
    [reitit.frontend :as rtf]
    [reitit.frontend.history :as rtfh]
    [reitit.frontend.easy :as rtfe]
    [reitit.coercion.schema :as rsc]
    [cljs-asciidoctor.use-cases.core-cases :as ccases]
    [cljs-asciidoctor.views.home :as home]
    [cljs-asciidoctor.views.compo :as compo]))

;;https://clojure.org/guides/weird_characters#__code_code_var_quote
(def routes
    (rtf/router
      ["/"
       [""
        {:name :routes/frontpage
         :view #'home/main}]
       ["component"
        {:name :routes/component
         :view #'compo/main}]]

      {:data {:coercion rsc/coercion}}))



(defn app-routes []

  (rtfe/start! routes
               (fn [m] (rf/dispatch [::ccases/set-active-panel m]))
               {:use-fragment true}))



