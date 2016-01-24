(ns codenames.config
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[codenames started successfully]=-"))
   :middleware identity})
