(ns codenames.routes.home
  (:require [codenames.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok]]
            [clojure.java.io :as io]
            [secret-agent.core :as game]
            [codenames.gamelayout :as gl]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page []
  (layout/render "about.html"))

(defn new-game []
  (def game-state (game/new-game))
  (layout/render "board.html" {:board (gl/spymaster-view game-state 5)}))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/new-game" [] (new-game)))

