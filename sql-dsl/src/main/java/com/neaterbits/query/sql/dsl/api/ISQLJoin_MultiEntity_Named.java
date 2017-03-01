package com.neaterbits.query.sql.dsl.api;

public interface ISQLJoin_MultiEntity_Named<MODEL, RESULT> extends ISQLJoin_Named_Base<MODEL, RESULT> {

	<LEFT, RIGHT> ISQLJoin_Condition_MultiEntity_Named<MODEL, RESULT, LEFT, RIGHT> innerJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
	
	<LEFT, RIGHT> ISQLJoin_Condition_MultiEntity_Named<MODEL, RESULT, LEFT, RIGHT> leftJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
}
