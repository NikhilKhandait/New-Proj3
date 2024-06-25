package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.BankDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class BankModelHibImp implements BankModelInt {

	public long add(BankDTO dto) throws ApplicationException, DuplicateRecordException {
		System.out.println("Bank ADD....hib");
		Session session = HibDataSource.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(dto);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
				session.close();
				System.out.println("Added Data...");

			}
		}
		return dto.getId();
	}

	public void delete(BankDTO dto) throws ApplicationException {

		Session session = HibDataSource.getSession();
		Transaction tx = session.beginTransaction();

		try {
			session.delete(dto);
			tx.commit();
		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
				session.close();
			}
			session.close();
		}

	}

	public void update(BankDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = HibDataSource.getSession();
		Transaction tx = session.beginTransaction();

		try {
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}

	}

	public BankDTO findByPK(long pk) throws ApplicationException {
		Session session = null;
		BankDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (BankDTO) session.get(BankDTO.class, pk);
		} catch (Exception e) {

		} finally {
			session.close();
		}
		return dto;
	}

	public List list() throws ApplicationException {

		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		List list = null;

		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(BankDTO.class);

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;

				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();
		} catch (Exception e) {
			session.close();
		}

		return list;
	}

	
	public List search(BankDTO dto, int pageNo, int pageSize) throws ApplicationException {
		System.out.println("List bank model....");

		Session session = null;
		ArrayList<BankDTO> list = null;

		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(BankDTO.class);

			if (dto != null) {
				if (dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getAccountNo() != null && dto.getAccountNo().length() > 0) {
					criteria.add(Restrictions.ilike("accountNo", dto.getAccountNo() + "%"));

				}

				if (pageNo > 0) {

					pageNo = (pageNo - 1) * pageSize;
					criteria.setFirstResult(pageNo);
					criteria.setMaxResults(pageSize);
				}
				list = (ArrayList<BankDTO>) criteria.list();

			}

		} catch (Exception e) {

		}finally {
			session.close();
		}
		return list;
	}

	
	public List search(BankDTO dto) throws ApplicationException {

		return search(dto, 0, 0);
	}

}
