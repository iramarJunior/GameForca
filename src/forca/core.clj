(ns forca.core
  (:gen-class))

(def total-de-vidas 6)
(def palavra "Melancia")

(defn perdeu [] (print "Você Perdeu!"))
(defn ganhou [] (print "Você Ganhou!"))

(defn letras-faltantes [palavra acertos]
  (remove (fn [letra] (contains? acertos (str letra))) palavra))

(defn acertou-a-palavra-toda? [palavra acertos] 
  (empty? letras-faltantes palavra acertos))

(defn le-letra! [] (read-line))

(defn acertou? [chute palavra] (.contains palavra chute))

(defn avalia-chute [chute vidas palavra acertos]
  (if (acertou? chute palavra)
    (jogo vidas palavra (conj acertos chute))
    (jogo (dec vidas) palavra acertos)))

(defn jogo [vidas palavra acertos]
  (if (= vidas 0)
    (perdeu)
    (if (acertou-a-palavra-toda? palavra acertos)
      (ganhou)
      (avalia-chute (le-letra!) vidas palavra acertos)
      (print "Chuta amigo."))))

(defn -main [& args]
  (jogo total-de-vidas)