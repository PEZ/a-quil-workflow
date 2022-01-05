(ns a-quil-workflow.core
  (:require [a-quil-workflow.drawing :as drawing]
            [quil.applet :as qa]
            [quil.core :as q]
            [quil.middleware :as qm]))

(q/defsketch example
  :title "An example quil sketch"
  :size [500 300]
  ; setup function called only once, during sketch initialization.
  :setup drawing/setup
  ; update-state is called on each iteration before draw-state.
  :update drawing/update-state
  :draw drawing/draw-state
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [qm/fun-mode])

(comment
  (use 'a-quil-workflow.drawing :reload)
  (qa/with-applet a-quil-workflow.core/example (q/random 10))
  (qa/with-applet a-quil-workflow.core/example (q/no-loop))
  (qa/with-applet a-quil-workflow.core/example (q/start-loop)))