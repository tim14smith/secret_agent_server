(ns codenames.gamelayout
  (:require [secret-agent.core :as game]
            [clojure.string :as strings]))

(defn spymaster-view-columns [game-state]
  (let [bystanders (:bystanders @game-state)
        reds (:red-agents @game-state)
        blues (:blue-agents @game-state)
        assassin (:assassin @game-state)
        agents (:agents @game-state)
        start-elem (fn [bg-clr txt-clr] (str "<td style=\"background-color:" bg-clr ";color:" txt-clr ";text-align:center\">"))]
    (for [agent agents]
      (let [agt-nm (:name agent)
            agt-dsply (if (:flipped? agent) "**********" agt-nm)]
        (if (some #{agt-nm} (map :name bystanders))
          (str (start-elem "grey" "black") agt-dsply "</td>")
               (if (some #{agt-nm} (map :name assassin))
                 (str (start-elem "black" "white") agt-dsply "</td>")
                 (if (some #{agt-nm} (map :name reds))
                   (str (start-elem "red" "white") agt-dsply "</td>")
                   (str (start-elem "blue" "white") agt-dsply "</td>"))))))))

(defn spymaster-view [game-state num-cols]
  (let [columns (codenames.gamelayout/spymaster-view-columns game-state)]
    (letfn [(addrows [cols]
              (if (empty? cols)
                []
                (concat
                 ["<tr>"] (take num-cols cols) ["</tr>"] (addrows (drop num-cols cols)))))]
      (str "<table style=\"width:100%;height:500px\">" (apply str (addrows columns)) "</table>"))))


(comment
  (def game-state (game/new-game))
  (spymaster-view game-state 5)
)
