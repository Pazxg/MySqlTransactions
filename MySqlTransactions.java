import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
 
public class JavaOrnekleri {
    
   static Connection con;
    
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");  
        }catch(Exception e){ System.out.println(e);}
       
        
        Scanner scan= new Scanner(System.in,"iso-8859-9");
        int secim;
        
        while(true)
        {
            System.out.println("*************");
            System.out.println("1.List");
            System.out.println("2.Add");
            System.out.println("3.Update");
            System.out.println("4.Remove");
            System.out.println("5.Exit");
            System.out.print("Choose:");
            secim=scan.nextInt();
 
            System.out.println("*************");
            
            if(secim==1) List();
            if(secim==2) Add();
            if(secim==3) Update();
            if(secim==4) Remove();
            if(secim==5) {
                try{
                    con.close(); 
                }catch(Exception e){ System.out.println(e);}
                
                break;
            }            
        }
    }
    
    public static void List()
    {
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from ogrenci"); 
            while(rs.next())  
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
 
        }catch(Exception e){ System.out.println(e);}
         
    }
    
    public static void Add()
    {
        Scanner scan= new Scanner(System.in,"iso-8859-9");
        System.out.print("Yeni Öğrenci No     :");
        int yenino = scan.nextInt();
        System.out.print("Yeni Öğrenci Adı    :");
        String ad=scan.next();
        System.out.print("Yeni Öğrenci Soyadı :");
        String soyad=scan.next();
        try{
            Statement stmt=con.createStatement(); 
            String sorgu=String.format("insert into ogrenci values( %d, '%s','%s')", yenino,ad,soyad);
            int ekleme = stmt.executeUpdate(sorgu);
            System.out.println("Kayıt Eklendi");
        }catch(Exception e){ System.out.println(e);}
        
            
    }
    
    public static void Update()
    {
        Scanner scan= new Scanner(System.in,"iso-8859-9");
        try{
            Listele();
            System.out.print("Öğrenci Numarasını Girin:");
            int eskino=scan.nextInt();
            System.out.print("Yeni Öğrenci No     :");
            int yenino = scan.nextInt();
            System.out.print("Yeni Öğrenci Adı    :");
            String ad=scan.next();
            System.out.print("Yeni Öğrenci Soyadı :");
            String soyad=scan.next();
            
            String sorgu=String.format("update ogrenci set ogrno=%d, ograd='%s',ogrsoyad='%s' where ogrno=%d ", yenino,ad,soyad,eskino) ;
            
            Statement stmt=con.createStatement(); 
            int guncelleme = stmt.executeUpdate(sorgu);  
            System.out.println("Kayıtlar Güncellendi");
        }catch(Exception e){ System.out.println(e);}
    }
    
    public static void Remove()
    {
        Scanner scan= new Scanner(System.in,"iso-8859-9");
        try{
            Listele();
            System.out.print("Öğrenci Numarasını Girin:");
            int eskino=scan.nextInt();  
            
            String sorgu=String.format("delete from ogrenci where ogrno=%d",eskino);
            Statement stmt=con.createStatement(); 
            int silindi = stmt.executeUpdate(sorgu);  
            System.out.println("Kayıtlar Silindi");
            
        }catch(Exception e){ System.out.println(e);}
    }
}