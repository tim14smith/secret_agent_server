(ns codenames.config
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [codenames.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[codenames started successfully using the development profile]=-"))
   :middleware wrap-dev})
