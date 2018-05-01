(ns bank.validation
  (:require [clojure.spec.alpha :as spec]
            [clara.rules :as clara :refer [insert fire-rules query defquery]]
            [clara.tools.inspect :refer [inspect explain-activations]]
            [bank.rules :refer [require-authorisation?]]
            [bank.specs :refer [map->Client map->Agent]]))


(defn check-subscription
  [client agent]
  (let [session (-> (clara/mk-session 'bank.rules)
                    (insert (map->Client client)
                            (map->Agent agent))
                    (fire-rules))]
    (clara.tools.inspect/inspect session)

    (query session require-authorisation?)))

(spec/fdef check-subscription
           :args (spec/cat :client :bank/client
                           :agent :bank/agent))


(comment
  (spec/exercise-fn `check-subscription)

  (check-subscription {:employment :employment/SelfEmployed}
                      {:delegation-level :delegation/low})
  )