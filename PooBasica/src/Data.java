// public class Data {
//     String data;

//     public Data(String data){

//     }

//     public void validarData(String data){
//         if(data.isBlank() || (!data.matches("\\d{2}/\\d{2}/\\d{4}"))){
//             throw new IllegalArgumentException("Data inválida");
//         }

//         String dataArray[] = data.split("/");

//         int dia = Integer.parseInt(dataArray[0]);
//         int mes = Integer.parseInt(dataArray[1]);
//         int ano = Integer.parseInt(dataArray[2]);

        

//     }

//     public Data adicionarDias(int dias){

//     }

//     public int[] dividirData(){
//         String dataArray[] = data.split("/");

//         // int dia = Integer.parseInt(dataArray[0]);
//         // int mes = Integer.parseInt(dataArray[1]);
//         // int ano = Integer.parseInt(dataArray[2]);

//         int dataInt[] = new int[3];

//         for(int i = 0; i < dataArray.length; i++){
//             dataInt[i] = Integer.parseInt(dataArray[i]);
//         }
//         return dataInt;
//     }
// }
