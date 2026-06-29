package com.github.nightfuze;

import java.util.Map;
import java.util.TreeMap;

public class Main {
    static void main() {
        Map<String, BankAccount> accountMap = new TreeMap<>();

        for (int i = 0; i < 100; i++) {
            BankAccount account = new BankAccount("Ivan");
            account.deposit(1000);
            account.withdraw(1001);
            account.withdraw(999);
            accountMap.put(account.getNumber(), account);
        }
        accountMap.forEach((number, bankAccount) -> System.out.printf("%s - %s\n", number, bankAccount));

        BankAccount my = new BankAccount("Dmitry");
        BankAccount other = new BankAccount("Maxim");
        BankAccount blockedAccount = new BankAccount("Andrey");
        blockedAccount.block();

        System.out.println();
        System.out.println("Пополнение средств на счет " + my.getNumber());
        deposit(my, 1000);
        deposit(my, 0);
        System.out.println("Пополнение средств на счет " + other.getNumber());
        deposit(other, 1500);
        deposit(other, -999);

        System.out.println();
        System.out.println(my);
        System.out.println(other);

        System.out.println();
        System.out.println("Вывод средств со счета " + my.getNumber());
        withdraw(my, 100);
        withdraw(my, 999);
        withdraw(my, 0);
        withdraw(my, -500);

        System.out.println();
        System.out.println(my);

        System.out.printf("\nПеревод средств cо счета %s на счет %s\n", my.getNumber(), other.getNumber());
        transfer(my, other, 500);
        transfer(my, other, 0);
        transfer(my, other, -100);
        transfer(my, other, 9999);
        transfer(my, other, 400);
        transfer(my, other, 1);

        System.out.println();
        System.out.println(my);
        System.out.println(other);

        System.out.println("\nПополнение средств на заблокированный счет " + blockedAccount.getNumber());
        deposit(blockedAccount, 1000);

        System.out.println("\nВывод средств с заблокированного счета " + blockedAccount.getNumber());
        withdraw(blockedAccount, 1000);

        System.out.printf("\nПеревод средств с заблокированного счета %s на счет %s\n", blockedAccount.getNumber(), other.getNumber());
        transfer(blockedAccount, other, 1000);

        System.out.printf("\nПеревод средств со счета %s на заблокированный счет %s\n", other.getNumber(), blockedAccount.getNumber());
        transfer(other, blockedAccount, 1000);

        System.out.println();
        System.out.println(blockedAccount);
        System.out.println(other);
    }

    static void deposit(BankAccount account, int amount) {
        System.out.printf("Попытка пополнения %d средств на счет %s (%s) баланс %d\n", amount, account.getName(), account.getNumber(), account.getBalance());
        if (!account.deposit(amount)) {
            System.out.printf("Ошибка пополнения: Неуспешное пополнение средств %d на счет %s (%s) баланс %d\n", amount, account.getName(), account.getNumber(), account.getBalance());
        } else {
            System.out.printf("Успешное пополнение: пополние %d средств на счет %s (%s) баланс %d\n", amount, account.getName(), account.getNumber(), account.getBalance());
        }
    }

    static void withdraw(BankAccount account, int amount) {
        System.out.printf("Попытка вывода %d средств от %s (%s) баланс %d\n", amount, account.getName(), account.getNumber(), account.getBalance());
        if (!account.withdraw(amount)) {
            System.out.printf("Ошибка вывода: Неуспешный вывод средств %d со счета %s (%s) баланс %d\n", amount, account.getName(), account.getNumber(), account.getBalance());
        } else {
            System.out.printf("Успешный вывод: вывод %d средств со счета %s (%s) баланс %d\n", amount, account.getName(), account.getNumber(), account.getBalance());
        }
    }

    static void transfer(BankAccount from, BankAccount to, int amount) {
        System.out.printf("Попытка перевода %d средств от %s (%s) баланс %d на %s (%s) баланс %d\n", amount, from.getName(), from.getNumber(), from.getBalance(), to.getName(), to.getNumber(), to.getBalance());
        if (!from.transfer(to, amount)) {
            System.out.printf("Ошибка перевода: Неуспешный перевод %s средств %d со своего счета (%s) баланс %d на счет %s (%s) баланс %d\n", from.getName(), amount, from.getNumber(), from.getBalance(), to.getName(), to.getNumber(), to.getBalance());
        } else {
            System.out.printf("Успешный перевод: %s перевел %d со своего счета (%s) баланс %d на счет %s (%s) баланс %d\n", from.getName(), amount, from.getNumber(), from.getBalance(), to.getName(), to.getNumber(), to.getBalance());
        }
    }
}
