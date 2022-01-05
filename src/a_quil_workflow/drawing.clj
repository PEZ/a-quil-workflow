(ns a-quil-workflow.drawing
  (:require [quil.core :as q]))

;; defonce so that we can reload the `draw-state` function
(defonce calva-logo (ref nil))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :hsb)
  (dosync (ref-set calva-logo (q/load-image "calva-symbol+logo.jpg")))
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:color 0
   :angle 0})

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  {:color (mod (+ (:color state) 0.7) 255)
   :angle (+ (:angle state) 0.1)})

(defn draw-state [state]
  #_(q/background 240)
  (q/image @calva-logo 0 0)
  ; Set circle color.
  (q/fill (:color state) 255 255 75)
  (q/stroke-weight 3)
  ; Calculate x and y coordinates of the circle.
  (let [angle (:angle state)
        _ (def angle angle)
        x (* 200 (q/cos angle))
        y (* 100 (q/sin (* 3 angle)))]
    ; Move origin point to the center of the sketch.
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ; Draw 
      (q/line 0 0 x y)
      (q/ellipse x y 80 80))))


(comment
  ;; Evaluate these using the custom commands shortcut `ctrl+space t`
  (q/current-fill)
  (q/frame-rate 60)
  [(q/mouse-x) (q/mouse-y)])