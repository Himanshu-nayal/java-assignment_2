
import org.w3c.dom.ls.LSOutput;
import java.util.*;
import java.io.*;

    interface Bank {

        int getDetails();
        void printDetails();
        void withdraw(int amount)throws BOI.InsufficientBalanceException,
                SBI.InsufficientBalanceException,ICICI.InsufficientBalanceException;
    }
    class SBI implements Bank,Serializable
    {
        public final static int savings = 3000;
        public final static int current = 6000;
        public int getDetails()
        {
            return 15;
        }

        public void printDetails()
        {
            System.out.println("SBI");

        }
        enum account {
            savings,
            current;

        }
        public void withdraw(int amount)throws InsufficientBalanceException {

            if (amount > savings) {
                throw new InsufficientBalanceException("INSUFFICIENT BALANCE");
            }
            else {
                int bal=savings- amount;
                System.out.print("BALANCE IS "+ " "+bal);

            }


        }

        protected static class InsufficientBalanceException extends Exception {
            public InsufficientBalanceException(String insufficient_balance) {
                super(insufficient_balance);
            }
        }
    }
    class BOI implements Bank,Serializable {

        public final static int savings = 7000;
        public final static int current = 8000;

        public int getDetails() {
            return 13;
        }

        public void printDetails() {
            System.out.println("BOI");

        }

        enum account {
            savings, current;
        }


        public void withdraw(int amount) throws InsufficientBalanceException {

            if (amount > savings) {
                throw new InsufficientBalanceException("INSUFFICIENT BALANCE");
            } else {
                int bal = savings - amount;
                System.out.println("BALANCE IS " + " " + bal);

            }


        }

        protected static class InsufficientBalanceException extends Exception {

            public InsufficientBalanceException(String insufficient_balance) {
                super(insufficient_balance);
            }
        }
    }
    class ICICI implements Bank,Serializable {
        public final static int savings = 8000;
        public final static int current = 9000;

        public int getDetails() {
            return 16;
        }

        public void printDetails() {
            System.out.println("ICICI");

        }

        enum account {
            savings, current;
        }

        public void withdraw(int amount) throws InsufficientBalanceException {

            if (amount > savings) {
                throw new InsufficientBalanceException("INSUFFICIENT BALANCE");
            } else {
                int bal = savings - amount;
                System.out.println("BALANCE IS " + " " + bal);

            }


        }

        protected static class InsufficientBalanceException extends Exception {
            public InsufficientBalanceException(String insufficient_balance) {
                super(insufficient_balance);

            }
        }
    }

    class InsufficientAmountException extends Exception {
        InsufficientAmountException(String s) {
            super(s);
        }
    }
    class M
    {
        public static void main(String[] args) throws BOI.InsufficientBalanceException, ICICI.InsufficientBalanceException, SBI.InsufficientBalanceException {

            BOI boi=new BOI();
            ICICI icici=new ICICI();
            SBI sbi=new SBI();

            System.out.println("THE RATE OF INTEREST OF SBI :" + sbi.getDetails());
            System.out.println("THE RATE OF INTEREST OF BOI :"+boi.getDetails());
            System.out.println("THE RATE OF INTEREST OF ICIC "+ icici.getDetails());


            boi.printDetails();
            icici.printDetails();
            sbi.printDetails();
            Scanner x=new Scanner(System.in);
            System.out.println("Enter amount to be withdrawn" );
            int amount=x.nextInt();
            System.out.println("Enter your bank " +"\n"+ "1.ICICI"+ "\n"+"2.BOI" +"\n" +"3.SBI");
            int choice=x.nextInt();
            switch(choice)
            {
                case 1:
                    sbi.withdraw(amount);
                    break;
                case 2:

                    icici.withdraw(amount);
                    break;
                case 3:
                    boi.withdraw(amount);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }

            //Every banking transaction should be saved in a file of each bank's
            // transaction log which would have all details of the transaction like
            // (time of transaction,account number,amount withdrawn, amount before deduction,
            // amount after deduction,transaction status (can also be enum), failure reason if any)
            String file="Log.txt";
            try {
                ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream(file));
                os.writeObject(sbi);
                os.writeObject(icici);
                os.writeObject(boi);
                os.close();
            }
            catch(FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            System.out.println("Done writing");








        }
    }


