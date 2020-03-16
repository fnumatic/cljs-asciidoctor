(ns cljs-asciidoctor.views.home
  (:require
   [re-frame.core :as rf]
   [cljs-asciidoctor.use-cases.core-cases :as ccases]
   [tools.viewtools :as vt]
   [cljs.pprint]
   ))



(defn txt-input []
 (let [inp (rf/subscribe [:text/input])]
  [:div.col.border.border-primary
   [:textarea.w-100
    {:style {:overflow :scroll}
     :value @inp
     :on-change #(rf/dispatch [:text/input (-> % .-target .-value)])
     :rows 30}]]))

(defn ascii-render []
  (let [html (rf/subscribe [:text/rendered])]
    [:div.col.border.border-success
     {:style {:height "550px"
              :width "100%"
              :display :inline-block
              :overflow :scroll}}
     [:div.w-30
      {

       :dangerouslySetInnerHTML {:__html @html}}]]))

(defn main []
  [:div.container
   [:h2 "home"]
   [:div.row
    [txt-input]
    [ascii-render]]
   [:div]])

(def toolbar-items
  [
   ["#" :routes/frontpage]
   ["component" :routes/component]])

;; main

(defn show-panel [route]
  (when-let [route-data (:data route)]
    (let [view (:view route-data)]
      [:<>
       [view]
       [:pre (with-out-str (cljs.pprint/pprint route))]])))

(defn main-panel []
  (let [active-route (rf/subscribe [::ccases/active-panel])]
    [:div
     [vt/navigation toolbar-items]
     [show-panel @active-route]]))
