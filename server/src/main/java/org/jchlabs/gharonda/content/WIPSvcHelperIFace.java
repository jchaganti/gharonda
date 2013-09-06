package org.jchlabs.gharonda.content;

import org.jchlabs.gharonda.domain.model.Contentholder;

public interface WIPSvcHelperIFace {

	public Contentholder checkin();

	public Contentholder checkout();

	public Contentholder undoCheckout();

}
