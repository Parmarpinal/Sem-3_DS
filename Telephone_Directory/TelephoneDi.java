import java.util.Scanner;
import java.io.*;
public class TelephoneDi{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
			Directory d1=new Directory();
			d1.oldData();
			int choice;
			do{
				System.out.println("\n");
				System.out.println("Enter 1 for insert\nEnter 2 for delete: ");
				choice=sc.nextInt();
				
				switch(choice){
					case 1:{
						d1.insert();
						d1.display();
						break;
					}
					case 2:{
						d1.delete();
						d1.display();
						break;
					}
					default:{
						System.out.println("============Wrong input=============");
						return;
					}
				}
			}while(choice<=2);
	}
}
class Directory{
	class Node{
		String name;
		String no;
		Node link;
		Node(String name,String no){
			this.name=name;
			this.no=no;
			this.link=null;
		}
	}

		Node first;
		Node last;
		public void oldData(){
			try{
				BufferedReader f1=new BufferedReader(new FileReader("abc.txt"));
				String line=f1.readLine();
				String n="",num="";
				String[] arr;
				while(line!=null){
					arr = line.split("-");
					Node newNode = new Node(arr[0],arr[1]);
					if(first==null){
						first=last=newNode;
					}else{
						last.link=newNode;
						last=newNode;
					}
					line=f1.readLine();
				}

			}
			catch(Exception e){
				System.out.println("There is no numbers in directory");
				System.out.println("<::::::::please enter number:::::>");
			}
		}
		public void insert(){
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter name :");
			String n=sc.nextLine();
			System.out.println("Enter number :");
			String number=sc.nextLine();
			Node newNode = new Node(n,number);
			if(first==null){
				first=last=newNode;
				return;
			}
			last.link=newNode;
			last=newNode;
		}															
		public void delete(){
			Scanner sc=new Scanner(System.in);
			if(first==null){
				System.out.println("======Telephone directory is empty======");
				return;
			}
			System.out.println("Enter name for delete :");
			String dname=sc.nextLine();
			Node save=first;
			Node pred=first;
			while(save!=null && !save.name.equals(dname)){
				System.out.println("name = "+save.name);
				System.out.println("pred = "+pred.name);
				pred=save;
				save=save.link;
			}
			if(save==first){
				first=save.link;
			}
			if(save==null){
				System.out.println("===========Name is not found============");
				return;
			}
			pred.link=save.link;
			if(save.link==null){
				last=pred;
			}
		}
		public void display(){

			Node save=first;
			try{
				FileWriter f1=new FileWriter("abc.txt");
				while(save!=null){
					f1.write(save.name+"-"+save.no);
					f1.write("\n");
					save=save.link;
				}
				f1.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
}