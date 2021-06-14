(ns cljs-asciidoctor.views.home
  (:require
   [re-frame.core :as rf]
   [cljs-asciidoctor.use-cases.core-cases :as ccases]
   [cljs.pprint]
   ))



(defn txt-input []
 (let [inp (rf/subscribe [:text/input])]
  [:div.flex.border-2.border-blue-600.h-full.w-full.overflow-scroll
   [:textarea.h-full.w-full.p-4.outline-none.focus:outline-none.resize-none.text-sm
    {
     :value @inp
     :on-change #(rf/dispatch [:text/input (-> % .-target .-value)])
     }]]))

(defn ascii-render []
  (let [html (rf/subscribe [:text/rendered])]
    [:div.flex.border-2.border-green-600.h-full.w-full.overflow-scroll
     [:div.w-30.p-4.h-full.prose.prose-sm
      {
       :dangerouslySetInnerHTML {:__html @html}}]]))

(defn main []
  [:div.container.mx-auto.px-4
   [:h2.font-semibold.text-xl.m-2.mb-3
    "AsciiDoc Converter"]
   [:div.flex.justify-items-stretch.items-stretch
    {:class ["h-[90vh]" :flex-col :space-y-2 "md:h-[80vh]" :md:flex-row :md:space-x-2 :md:space-y-0]}
    [txt-input]
    [ascii-render]]
   [:div]])



;; main

(defn show-panel [route]
  (when-let [route-data (:data route)]
    (let [view (:view route-data)]
      [:<>
       [view]
       ;[:pre (with-out-str (cljs.pprint/pprint route))]
       ,])))

(defn main-panel []
  (let [active-route (rf/subscribe [::ccases/active-panel])]
    [:div
     [show-panel @active-route]]))
