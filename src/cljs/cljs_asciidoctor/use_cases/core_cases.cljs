(ns cljs-asciidoctor.use-cases.core-cases
  (:require
   [re-frame.core :as re-frame]
   [cljs-asciidoctor.db :as db]
   [tools.reframetools :refer [sdb gdb]]
   [asciidoctor]))

   ;[day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))


(def addd (asciidoctor.))
(.log js/console (.convert addd ""))


(defn rendered [txt _]
 (.convert addd (or txt "")))

(re-frame/reg-sub :text/input (gdb [:text/input]))
(re-frame/reg-sub :text/rendered
                  :<- [:text/input]
                  rendered)
(re-frame/reg-sub ::active-panel (gdb [:active-panel]))
(re-frame/reg-sub ::re-pressed-example  (gdb [:re-pressed-example]))

(re-frame/reg-event-db ::initialize-db (constantly db/default-db))
(re-frame/reg-event-db :text/input  (sdb [:text/input]))
(re-frame/reg-event-db ::set-active-panel [re-frame/debug] (sdb [:active-panel]))


