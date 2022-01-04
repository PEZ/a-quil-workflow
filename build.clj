(ns build
  (:refer-clojure :exclude [test])
  (:require [org.corfield.build :as bb]))

(def lib 'net.clojars.pez/a-quil-workflow)
(def version "0.0.1-SNAPSHOT")
(def main 'a-quil-workflow.core)

(defn test "Run the tests." [opts]
  (bb/run-tests opts))

(defn ci "Run the CI pipeline of tests (and build the uberjar)." [opts]
  (-> opts
      (assoc :lib lib :version version :main main)
      (bb/run-tests)
      (bb/clean)
      (bb/uber)))
