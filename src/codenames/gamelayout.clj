(ns codenames.gamelayout
  (:require [secret-agent.core :as game]
            [clojure.string :as strings]))

(defn spymaster-view-columns [game-state]
  (let [bystanders (map :name (:bystanders @game-state))
        reds (map :name (:red-agents @game-state))
        blues (map :name (:blue-agents @game-state))
        assassin (map :name (:assassin @game-state))
        agents (map :name (:agents @game-state))
        start-elem (fn [bg-clr txt-clr] (str "<td style=\"background-color:" bg-clr ";color:" txt-clr ";text-align:center\">"))]
           (for [agent agents]
             (if (some #{agent} bystanders)
               (str (start-elem "grey" "black") agent "</td>")
               (if (some #{agent} assassin)
                 (str (start-elem "black" "white") agent "</td>")
                 (if (some #{agent} reds)
                   (str (start-elem "red" "white") agent "</td>")
                   (str (start-elem "blue" "white") agent "</td>")))))))

(defn spymaster-view [game-state num-cols]
  (let [columns (codenames.gamelayout/spymaster-view-columns game-state)]
    (letfn [(addrows [cols]
              (if (empty? cols)
                []
                (concat
                 ["<tr>"] (take num-cols cols) ["</tr>"] (addrows (drop num-cols cols)))))]
      (str "<table style=\"width:100%;height:500px\">" (apply str (addrows columns)) "</table>"))))



(def game-state (game/new-game))
(spymaster-view game-state 5)
