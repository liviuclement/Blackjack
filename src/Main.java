import pack.Dealer;
import pack.Pack;
import pack.Player;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
            Player p1 = new Player();
            Dealer dealer = new Dealer();
            Pack pack = new Pack();
            p1.intro();
            System.out.println();
            pack.setPack(pack.shuffleCards());
            Scanner scanner = new Scanner(System.in);

            String scan;

            while (p1.keepPlaying) {
                p1.bet();
                p1.addCardToHand(pack.drawCard());
                p1.addCardToHand(pack.drawCard());
                dealer.addCardToHand(pack.drawCard());
                dealer.addCardToHand(pack.drawCard());

                check(scanner,dealer,p1,pack);


                if (p1.getMoney() > 0) {
                    pack.setPack(pack.shuffleCards());
                    p1.refreshHand();
                    dealer.refreshHand();
                    System.out.println("Do you want to keep playing?");
                    scan = scanner.nextLine();
                    if (!(scan.indexOf('y') == 0 || scan.indexOf('Y') == 0))
                        p1.keepPlaying = false;
                } else
                {
                    p1.keepPlaying = false;
                    System.out.println("You're out of money");
                }

            }
        }

        public static int check(Scanner scanner, Dealer dealer, Player p1, Pack pack)
        {
            String scan;
            if (p1.getTotal() == 21) {
                System.out.println("Blackjack! You win!");
                getPaid(p1,2);
            }
            else {
                if (p1.aceValue() > 0) {
                    System.out.println("You have: " + p1.getHand().toString() + " and a total of " + p1.getTotal() + "/" + (p1.getTotal() - 10));
                } else {
                    System.out.println("You have: " + p1.getHand().toString() + " and a total of " + p1.getTotal());
                }
                System.out.println("Dealer has: [" + dealer.getHand().get(1).toString() + ", Hidden]");
                System.out.println();
                System.out.println("Stand or hit? ");
                System.out.println();
                scan = scanner.nextLine();
                if (scan.indexOf('s') == 0 || scan.indexOf('S') == 0) {
                    stand(scanner,dealer, p1, pack);
                } else if (scan.indexOf('h') == 0 || scan.indexOf('H') == 0) {
                    hit(scanner, dealer, p1, pack);
                }
            }
            return 0;
        }

        public static int checkWin(Scanner scanner, Dealer dealer, Player p1, Pack pack)
        {
            if (dealer.getTotal() > 21 && dealer.aceValue() > 0)
            {

                dealer.setTotal(dealer.getTotal() - dealer.aceValue() * 10);
                return check(scanner,dealer,p1,pack);
            }
            else
            if(dealer.getTotal() > 21)
            {
                System.out.println("Dealer has: " + dealer.getHand().toString() + " and a total of " + dealer.getTotal());
                System.out.println();
                System.out.println("Dealer Busts. You win!");
                getPaid(p1,2);
            }
            else
            if(p1.getTotal() > 21 && p1.aceValue() > 0)
            {
                p1.setTotal(p1.getTotal() - p1.aceValue() * 10);
                return check(scanner,dealer,p1,pack);
            }
            else
            if(p1.getTotal() > 21)
            {
                System.out.println("You have: " + p1.getHand().toString() + " and a total of " + dealer.getTotal());
                System.out.println();
                System.out.println("You bust! Dealer wins.");
                getPaid(p1,0);
            }
            else
            if(p1.getTotal() == dealer.getTotal() && p1.getTotal() <= 21)
            {
                System.out.println("You have: " + p1.getHand().toString() + " and a total of " + dealer.getTotal());
                System.out.println();
                System.out.println("Push.");
                getPaid(p1,1);
            }
            else
            if(dealer.getTotal() == 21)
            {
                System.out.println("Dealer has" +dealer.getHand().toString() + " and a total of " + dealer.getTotal());
                System.out.println();
                System.out.println("Dealer has blackjack! You lose.");
                getPaid(p1,0);
            }
            else
            if(p1.getTotal() == 21)
            {
                System.out.println("You have: " + p1.getHand().toString() + " and a total of " + dealer.getTotal());
                System.out.println();
                System.out.println("Blackjack! You win!");
                getPaid(p1,3);
            }
            else
            if (p1.getTotal() > dealer.getTotal()) {
                System.out.println("Dealer has: " + dealer.getHand().toString() + " and a total of " + dealer.getTotal());
                System.out.println();
                System.out.println("You win!");
                getPaid(p1,2);
            }
            else
            if (dealer.getTotal() > p1.getTotal())
            {
                System.out.println("Dealer has: " + dealer.getHand().toString() + " and a total of " + dealer.getTotal());
                System.out.println();
                System.out.println("You lose!");
                getPaid(p1,0);
            }
                return 0;
        }

        public static void getPaid(Player p1, int result)
        {
            p1.setMoney(p1.getMoney() + result * p1.getBet());
        }

        public static int stand(Scanner scanner, Dealer dealer, Player p1, Pack pack)
        {
            if(dealer.aceValue() > 0 && dealer.getTotal() > 21)
            {
                dealer.setTotal(dealer.getTotal() - dealer.aceValue() * 10);
                return check(scanner,dealer,p1,pack);
            }
            else
            while (dealer.getTotal() < 17 && dealer.getTotal() <= p1.getTotal())
                dealer.addCardToHand(pack.drawCard());
                checkWin(scanner,dealer,p1,pack);
                return 0;
        }

        public static int hit(Scanner scanner, Dealer dealer, Player p1, Pack pack)
        {
            String scan;
            p1.addCardToHand(pack.drawCard());
                if (p1.getTotal() > 21 && p1.aceValue() > 0)
                {
                    p1.setTotal(p1.getTotal() - p1.aceValue() * 10);
                    return check(scanner,dealer,p1,pack);
                }
                else if (p1.getTotal() > 21){
                    System.out.println("You have: " + p1.getHand().toString() + " and a total of " + p1.getTotal());
                    System.out.println("You bust! Dealer wins.");
                    getPaid(p1,0);
                }
               else
                   if(p1.getTotal() == 21) {
                       System.out.println("You have: " + p1.getHand().toString() + " and a total of " + p1.getTotal());
                       System.out.println("Blackjack! You win.");
                       getPaid(p1, 3);
                   }
                   else
                       {
                           System.out.println("You have: " + p1.getHand().toString() + " and a total of " + p1.getTotal());
                           System.out.println();
                           System.out.println("Stand or hit? ");
                           scan = scanner.nextLine();
                           if (scan.indexOf('s') == 0 || scan.indexOf('S') == 0)
                               stand(scanner,dealer, p1, pack);
                           else if (scan.indexOf('h') == 0 || scan.indexOf('H') == 0)
                               hit(scanner, dealer, p1, pack);
                       }
            return 0;
        }


}