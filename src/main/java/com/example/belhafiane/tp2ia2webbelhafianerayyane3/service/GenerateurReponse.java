package com.example.belhafiane.tp2ia2webbelhafianerayyane3.service;

import jakarta.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.Locale;

/**
 * Classe de service pour les modificateurs de question.
 * Un modificateur de question prend en entrée une question et retourne
 * la question modifiée.
 */
@Dependent // Portée CDI pour rendre la classe injectable par CDI dans les autres classes.
// Portée Dependent : l'instance sera supprimée quand la classe qui l'a injectée sera supprimée.
public class GenerateurReponse implements Serializable { // Car CDI peut mettre l'instance en mémoire secondaire.
    /**
     * Modificateur de question.
     * @param question La question à modifier.
     * @param roleSysteme Le rôle système à utiliser pour la modification de la question.
     * @return La question modifiée : le rôle système en majuscule au début de la question, s'il n'est pas null,
     * suivi d'un saut de ligne,
     * puis la question en minuscule, le tout entouré de "||".
     */
//    public String genererReponse(String question, String roleSysteme) {
//        String resultat = "||";
//        if (roleSysteme != null) {
//            // Ajouter le rôle système en majuscule au début du résultat, suivi d'un saut de ligne.
//            resultat += roleSysteme.toUpperCase(Locale.FRENCH) + "\n";
//        }
//        resultat += question.toLowerCase(Locale.FRENCH) + "||";
//        return resultat;
//    }
    public String genererReponse(String question, String roleSysteme) {
        String texte = question.toLowerCase(Locale.ROOT);
            if (texte.contains("examen") || texte.contains("révision")) {
                return "Conseil pour les examens : révise par petites séances de 25 minutes et teste-toi sans regarder tes notes.\n";
            }

            if (texte.contains("java") || texte.contains("programmation") || texte.contains("code")) {
                return "Conseil programmation : essaie de résoudre le problème étape par étape avant d'écrire ton code.\n";
            }

            if (texte.contains("fatigue") || texte.contains("stress")) {
                return "Conseil bien-être : prends une courte pause, bois de l'eau et reprends ton travail avec un objectif simple.\n";
            }

            if (texte.contains("temps") || texte.contains("organisation")) {
                return "Conseil organisation : note trois tâches prioritaires et commence par la plus importante.\n";
            }

            if (texte.contains("bonjour") || texte.contains("salut")) {
                return "Bonjour ! Je suis ton conseiller étudiant. Parle-moi de tes études ou de ton organisation.\n";
            }
            return "Conseil du jour : transforme ton objectif en une petite action réalisable dès maintenant.\n";
    }

}