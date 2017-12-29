package cxb.chen.urights.common;

import cxb.chen.common.base.BaseResult;

/**
 * @author chen
 */
public class UrightsResult extends BaseResult{

    public UrightsResult(UrightsResultConstant urightsResultConstant, Object data) {
        super(urightsResultConstant.getCode(), urightsResultConstant.getMessage(), data);
    }
}
