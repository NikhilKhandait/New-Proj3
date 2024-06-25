package in.co.rays.project_3.test;

import in.co.rays.project_3.dto.BankDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.BankModelHibImp;
import in.co.rays.project_3.model.BankModelInt;


public class BankTest {
	public static BankModelInt model = new BankModelHibImp();

	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {

		BankModelInt model = new BankModelHibImp();

		//testAdd();
		testUpdate();
	}

	private static void testUpdate() throws ApplicationException, DuplicateRecordException {
		BankDTO dto = new BankDTO();
		dto.setId(5L);
		dto.setName("PNB");
		dto.setAccountNo("889999");
		dto.setAmount("81000");
		dto.setBranch("Reva");
		
		
		model.update(dto);
		
	}

	private static void testAdd() throws ApplicationException, DuplicateRecordException {

		BankDTO dto = new BankDTO();

		
		dto.setName("Canera");
		dto.setAccountNo("667777");
		dto.setAmount("91000");
		dto.setBranch("Devas");
		model.add(dto);

	}
}
