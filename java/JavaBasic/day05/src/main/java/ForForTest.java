/*
Ƕ��ѭ����ʹ��
1.Ƕ��ѭ������һ��ѭ���ṹA��������һ��ѭ���ṹB��ѭ������,�͹�����Ƕ��ѭ��

2.
���ѭ����ѭ���ṹB
�ڲ�ѭ����ѭ���ṹA

3. ˵��
�� �ڲ�ѭ���ṹ����һ�飬ֻ�൱�����ѭ��ѭ����ִ����һ��
�� �������ѭ����Ҫִ��m�Σ��ڲ�ѭ����Ҫִ��n�Ρ���ʱ�ڲ�ѭ����ѭ����һ��ִ����m * n��

4. ���ɣ�
   ���ѭ�������������ڲ�ѭ����������
*/
class ForForTest {
	public static void main(String[] args) {
		
		//******
		//System.out.println("******");
		for(int i = 1;i <= 6;i++){
			System.out.print('*');
		}

		System.out.println("\n");

		/*
		******
		******
		******
		******
		*/
		for(int j = 1;j <= 4;j++ ){
			for(int i = 1;i <= 6;i++){
				System.out.print('*');
			}
			System.out.println();
		}

		/*			i(�к�)		j(*�ĸ���)
		*			1			1
		**			2			2
		***			3			3
		****		4			4
		*****		5			5
		*/

		for(int i = 1;i <= 5;i++){//��������
			for(int j = 1;j <= i;j++){//��������
				System.out.print("*");
			
			}
			System.out.println();
		}
		
		/*			i(�к�)		j(*�ĸ���)   ���ɣ�i + j = 5 ���仰˵��j = 5 - i;
		****		1			4
		***			2			3
		**			3			2
		*			4			1
		*/	

		for(int i = 1;i <= 4;i++){
			for(int j = 1;j <= 5 - i;j++){
				System.out.print("*");	
			}
			System.out.println();
		}

		/*
		*
		**
		***
		****
		*****
		****
		***
		**
		*
		*/

		//��

/*

----* 
---* * 
--* * * 
-* * * * 
* * * * * 
 * * * * 
  * * * 
   * * 
    * 

*/

	//�ϰ벿��


	//�°벿��
		
	}
}