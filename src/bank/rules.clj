(ns bank.rules
  (:require [clara.rules :as clara :refer [defrule insert! defquery]]
            [bank.specs])

  (:import [bank.specs Client Agent AuthorisationRequired]))


(defrule RULE-RISKY-CLIENT
  [Client (= employment :employment/SelfEmployed)]
  [Agent (= delegation-level :delegation/low)]
  =>
  (insert! (AuthorisationRequired. :agent-delegation-client-employment)))


(defquery require-authorisation?
  "Query the session"
  []
  [?authorisation-required <- AuthorisationRequired])





