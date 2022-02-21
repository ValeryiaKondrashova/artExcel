package art;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final int TABLE_SIZE = 7;


        System.out.print("Введите путь к файлу: ");
        Scanner in = new Scanner(System.in);
        String num = in.next();

        ArrayList<String> list = new ArrayList<>(); // ArrayList имеет динамический размер. Когда мы добавляем элементы в ArrayList, его емкость увеличивается автоматически. Array в данном случае не подойдет
        try (Scanner scan = new Scanner(new File(num))) { //считывание данных из файла
            while (scan.hasNextLine()) {
                list.add(scan.nextLine());
            }

            String[] array = list.toArray(new String[0]);
            int code = 65;
            HeaderTable(TABLE_SIZE, array, code);

            System.out.println("\n");
            for(int i=0; i<array.length;i++){
//                int indexV = array[i].indexOf("=");
//                if(indexV>0) {
//
//                    resultStrStart = array[i].substring(array[i].indexOf("=") + 1, array[i].indexOf('=')+3);
//                    resultStrEnd = array[i].substring(array[i].indexOf("+") + 1, arraySizeEl);
//                    System.out.println("\tresultStrStart+resultStrEnd" + resultStrStart);
//                    System.out.println("\tresultStrStart+resultStrEnd" + resultStrEnd);
//                }

                char sign = '\'';
                int index;

                index = array[i].indexOf(sign);
                String newS = null;  // тут будет строка с удалённым символом
                if(index>0){
                    try
                    {
                        newS = array[i].substring(0, array[i].indexOf(sign));
                        newS += array[i].substring(array[i].indexOf(sign) + 1);
                    }
                    catch(Exception ex) {
                        array[i]="#";
                    }
                    array[i]=newS;
                    //System.out.println("\tarray[i]: " + newS);
                }

                sign = '+';
                index = array[i].indexOf(sign);
                array[i]=OutputValue(index, array[i], sign);

                sign = '-';
                index = array[i].indexOf(sign);
                array[i]=OutputValue(index, array[i], sign);

                sign = '*';
                index = array[i].indexOf(sign);
                array[i]=OutputValue(index, array[i], sign);

                sign= '/';
                index = array[i].indexOf(sign);
                array[i]=OutputValue(index, array[i], sign);
            }
            System.out.println("Значения после вычислений:");
            code = 65;
            HeaderTable(TABLE_SIZE, array, code);

        } catch (FileNotFoundException e) {
            System.out.println("Возникла ошибка при чтении файла!");
            e.printStackTrace();

        }
    }

    public static void HeaderTable(int TABLE_SIZE, String[] array, int code) { // во избежания повторения строк кода создана функция для вывода "шапки" таблицы
        for(int i=0;i<TABLE_SIZE;i++){
            char ch = (char) code;
            System.out.print("|\t\t" + ch + "\t\t|");
            code++;
        }
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------");
        for(int i=0; i<array.length;i++){
            System.out.print("|\t\t" + array[i] + "\t\t|");
            if (i%TABLE_SIZE-6==0){
                System.out.println("\n");
            }
        }
    }
    public static String OutputValue(int index, String value, char sign) {
        String resultStrStart = "";
        String resultStrEnd = "";
        int arraySizeEl = value.length(); //размер элемента из массива
        if(index>0){ //проверяем на наличие в строке знака

            resultStrStart = value.substring(0,value.indexOf(sign)); //считываем до знака
            resultStrEnd = value.substring(value.indexOf(sign)+1,arraySizeEl); //считываем после знака до конца(знак не включая)

            int resultStrStartInt = Integer.parseInt(resultStrStart);
            int resultStrEndInt = Integer.parseInt(resultStrEnd);
            //алгоритмические вычисления в зависимости от знака
            if(sign=='+'){
                value=Integer.toString(resultStrStartInt+resultStrEndInt);
            }
            if(sign=='-'){
                value=Integer.toString(resultStrStartInt-resultStrEndInt);
            }
            if(sign=='*'){
                value=Integer.toString(resultStrStartInt*resultStrEndInt);
            }
            if(sign=='/'){
                value=Integer.toString(resultStrStartInt/resultStrEndInt);
            }
        }
        return value;
    }
}
