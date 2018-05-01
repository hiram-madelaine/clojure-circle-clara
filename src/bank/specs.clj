(ns bank.specs
  (:require [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as gen]))


    (spec/def :bank.client/employment #{:employment/Employed
                                        :employment/Homemaker
                                        :employment/FullTimeEducation
                                        :employment/SelfEmployed
                                        :employment/Unemployed})

    (spec/def :bank/client (spec/keys :req-un [:bank.client/employment]))


    (spec/def :bank.agent/delegation-level #{:delegation/low
                                             :delegation/mid
                                             :delegation/full})

    (spec/def :bank/agent (spec/keys :req-un [:bank.agent/delegation-level]))


(defrecord Client [employment])

(defrecord Agent [delegation-level])

(defrecord AuthorisationRequired [reason])

