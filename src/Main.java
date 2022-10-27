import  java.util.Random;

public class Main {

    public static int[] heroesHealth = {300, 280, 260, 350};

    public static int[] heroesDamage = {20, 15, 25, 0};

    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int bossHealth = 900;
    public static int bossDamage = 50;

    public static String bossDefenceType = "";

    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }

    }

    public static void printStatistics() {
        System.out.println("********" + roundNumber + " ROUND ******");
        System.out.println("Boss Health: " + bossHealth + " [ " + bossDamage + " ] ");

        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " [ " + heroesDamage[i] + "]");
        }

        System.out.println();

    }

    public static void round() {
        roundNumber++;
        chooseBossAttackType();
        bossHits();
        heroesHit();
        medicHealHeroes();
        printStatistics();
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < bossDamage) {
                heroesHealth[i] = 0;
            } else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }

    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random r = new Random();
                    int coef = r.nextInt(8) + 2;
                    int heroCriticalDamage = heroesDamage[i] * coef;
                    if (bossHealth < heroCriticalDamage) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroCriticalDamage;
                    }
                    System.out.println("Critical damage: " + heroCriticalDamage +
                            "Hero attack type: " + heroesAttackType[i]);
                    System.out.println();
                } else {
                    if (bossHealth < heroesDamage[i]) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }

    }


    public static Boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes win!");
            return true;
        }

        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0) {
            System.out.println("Boss wins!");
            return true;
        }

        return false;

    }

    public static void chooseBossAttackType() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
    }
    public static void medicHealHeroes(){
        for (int i = 0; i  < heroesHealth.length; i++){
            if (heroesHealth[i] <100 && heroesHealth[i] > 0
               && heroesHealth[3] > 0 && i !=3 );{
                   Random medicHeal = new Random();
                   int healHeroes = medicHeal.nextInt(50);
                   heroesHealth[i] = heroesHealth[i] + healHeroes;
                System.out.println(heroesAttackType[i] + " + " + healHeroes);
                break;
            }

        }
    }

    private static class Random {
    }
}



