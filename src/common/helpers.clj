(ns common.helpers
  (:require [clojure.string :as str]))

(defn input
  "Retrieve the files input in split lines"
  [file]
  (str/split-lines (slurp file)))