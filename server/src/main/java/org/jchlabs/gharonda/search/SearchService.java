package org.jchlabs.gharonda.search;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jchlabs.gharonda.domain.model.BitwiseAndSearchCriteria;
import org.jchlabs.gharonda.domain.model.DoubleRangeSearchCriteria;
import org.jchlabs.gharonda.domain.model.InValuesNumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.NumberRangeSearchCriteria;
import org.jchlabs.gharonda.domain.model.NumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.StringEqualSearchCriteria;
import org.jchlabs.gharonda.domain.model.StringSearchCriteria;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao._RootDAO;
import org.jchlabs.gharonda.shared.rpc.FetchProfile;


public class SearchService implements SearchServiceIFace {
	private Class classType;
	private _RootDAO dao;
	private Criteria criteria;
	private List<SearchCriteriaIFace> orCList;
	private FetchProfile fetchProfile;

	public SearchService(Class classType, _RootDAO dao, FetchProfile fetchProfile) {
		this.classType = classType;
		this.dao = dao;
		this.fetchProfile = fetchProfile;
	}

	public SearchService(Class classType, _RootDAO dao) {
		this(classType, dao, null);
	}

	public List<Object> search(List<SearchCriteriaIFace> cList) {
		List<Object> result = null;
		if (classType != null && dao != null) {
			criteria = dao.getSession().createCriteria(classType);
			updateAllCriteria(cList);
			result = criteria.list();
		}
		return result;
	}

	public List<Object> search(List<Object> oList, List<SearchCriteriaIFace> cList) {
		List<Object> result = null;
		if (classType != null && dao != null) {
			criteria = dao.getSession().createCriteria(classType);
			updateAllCriteria(cList);
			oList.retainAll(criteria.list());
			result = oList;
		}
		return result;

	}

	public List<Object> search(List<SearchCriteriaIFace> cList, Users user) {
		List<Object> result = null;
		if (classType != null && dao != null) {
			criteria = dao.getSession().createCriteria(classType);
			updateAllCriteria(cList);
			criteria.add(Restrictions.eq("User", user));
			result = criteria.list();
		}
		return result;

	}

	public List<Object> search(List<Object> oList, List<SearchCriteriaIFace> cList, Users user) {
		List<Object> result = null;
		if (classType != null && dao != null) {
			criteria = dao.getSession().createCriteria(classType);
			updateAllCriteria(cList);
			criteria.add(Restrictions.eq("User", user));
			oList.retainAll(criteria.list());
			result = oList;
		}
		return result;

	}

	private void updateAllCriteria(List<SearchCriteriaIFace> list) {
		if (criteria != null && list != null) {
			for (SearchCriteriaIFace val : list) {
				Criterion c = null;
				if (val instanceof StringEqualSearchCriteria) {
					c = getCriteriaWithStringEqualSearch((StringEqualSearchCriteria) val);
				} else if (val instanceof StringSearchCriteria) {
					c = getCriteriaWithStringSearch((StringSearchCriteria) val);
				} else if (val instanceof NumberSearchCriteria) {
					c = getCriteriaWithNumberSearch((NumberSearchCriteria) val);
				} else if (val instanceof NumberRangeSearchCriteria) {
					c = getCriteriaWithNumberRangeSearch((NumberRangeSearchCriteria) val);
				} else if (val instanceof InValuesNumberSearchCriteria) {
					c = getCriteriaWithInValuesNumberSearch((InValuesNumberSearchCriteria) val);
				} else if (val instanceof DoubleRangeSearchCriteria) {
					c = getCriteriaWithDoubleRangeSearch((DoubleRangeSearchCriteria) val);
				} else if (val instanceof BitwiseAndSearchCriteria) {
					c = getCriteriaWithBitwiseAndSearch((BitwiseAndSearchCriteria) val);
				}
				if (c != null) {
					criteria.add(c);
				}
			}
		}
		if (criteria != null && orCList != null) {
			Disjunction disjunction = Restrictions.disjunction();
			for (SearchCriteriaIFace val : orCList) {
				Criterion c = null;
				if (val instanceof StringEqualSearchCriteria) {
					c = getCriteriaWithStringEqualSearch((StringEqualSearchCriteria) val);
				} else if (val instanceof StringSearchCriteria) {
					c = getCriteriaWithStringSearch((StringSearchCriteria) val);
				} else if (val instanceof NumberSearchCriteria) {
					c = getCriteriaWithNumberSearch((NumberSearchCriteria) val);
				} else if (val instanceof NumberRangeSearchCriteria) {
					c = getCriteriaWithNumberRangeSearch((NumberRangeSearchCriteria) val);
				} else if (val instanceof InValuesNumberSearchCriteria) {
					c = getCriteriaWithInValuesNumberSearch((InValuesNumberSearchCriteria) val);
				} else if (val instanceof DoubleRangeSearchCriteria) {
					c = getCriteriaWithDoubleRangeSearch((DoubleRangeSearchCriteria) val);
				} else if (val instanceof BitwiseAndSearchCriteria) {
					c = getCriteriaWithBitwiseAndSearch((BitwiseAndSearchCriteria) val);
				}
				if (c != null) {
					disjunction.add(c);
				}
			}
			criteria.add(disjunction);
		}
		// criteria.setMaxResults(3);
		if (fetchProfile != null) {
			if (fetchProfile.getResultsSize() > 0) {
				criteria.setMaxResults(fetchProfile.getResultsSize());
			}

			if (fetchProfile.getFirstResult() > 0) {
				criteria.setFirstResult(fetchProfile.getFirstResult());
			}

			if (fetchProfile.getSortAttr() != null) {
				if (fetchProfile.isSortDir()) {
					criteria.addOrder(Order.asc(fetchProfile.getSortAttr()));
				} else {
					criteria.addOrder(Order.desc(fetchProfile.getSortAttr()));
				}

			}

		}
	}

	private Criterion getCriteriaWithInValuesNumberSearch(InValuesNumberSearchCriteria _c) {
		return (Restrictions.in(_c.getSearchAttributeName(), _c.getInValuesNumber()));

	}

	private Criterion getCriteriaWithStringSearch(StringSearchCriteria _c) {
		return (Restrictions.ilike(_c.getSearchAttributeName(), _c.getSearchString().replaceAll("\\*", "%"),
				MatchMode.START));
	}

	private Criterion getCriteriaWithStringEqualSearch(StringEqualSearchCriteria _c) {
		return (Restrictions.eq(_c.getSearchAttributeName(), _c.getSearchString()));
	}

	private Criterion getCriteriaWithNumberSearch(NumberSearchCriteria _c) {
		return (Restrictions.eq(_c.getSearchAttributeName(), _c.getSearchNumber()));
	}

	private Criterion getCriteriaWithNumberRangeSearch(NumberRangeSearchCriteria _c) {
		Criterion c = null;
		Integer lVal = _c.getLSearchNumber();
		Integer rVal = _c.getRSearchNumber();
		String searchAttributeName = _c.getSearchAttributeName();
		boolean inBetween = false;
		if (_c.getIsInclusive()) {
			if (lVal != null && rVal == null) {
				c = (Restrictions.ge(searchAttributeName, lVal));
			} else if (lVal == null && rVal != null) {
				c = (Restrictions.le(searchAttributeName, rVal));
			} else {
				inBetween = true;
			}
		} else {
			if (lVal != null && rVal == null) {
				c = (Restrictions.gt(searchAttributeName, lVal));
			} else if (lVal == null && rVal != null) {
				c = (Restrictions.lt(searchAttributeName, rVal));
			} else {
				inBetween = true;
			}
		}
		// TODO - Check if"between" here means inclusive or not
		if (inBetween) {
			c = (Restrictions.between(searchAttributeName, lVal, rVal));
		}

		return c;
	}

	private Criterion getCriteriaWithDoubleRangeSearch(DoubleRangeSearchCriteria _c) {
		Criterion c = null;
		Double lVal = _c.getLSearchDouble();
		Double rVal = _c.getRSearchDouble();
		String searchAttributeName = _c.getSearchAttributeName();
		System.out.println("The " + searchAttributeName + " = " + lVal + " : " + rVal);
		c = (Restrictions.between(searchAttributeName, lVal, rVal));

		return c;
	}

	private Criterion getCriteriaWithBitwiseAndSearch(BitwiseAndSearchCriteria _c) {
		String sql = " {alias}." + _c.getSearchAttributeName() + "& " + _c.getSearchNumber().toString() + " = "
				+ _c.getSearchNumber().toString();
		return (Restrictions.sqlRestriction(sql));
	}

	public void setORSearchCriteriaList(List<SearchCriteriaIFace> orCList) {
		this.orCList = orCList;
	}

	public FetchProfile getFetchProfile() {
		return fetchProfile;
	}

	public void setFetchProfile(FetchProfile fetchProfile) {
		this.fetchProfile = fetchProfile;
	}

}
