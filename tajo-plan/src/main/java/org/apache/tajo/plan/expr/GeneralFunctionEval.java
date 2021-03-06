/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.tajo.plan.expr;

import com.google.gson.annotations.Expose;
import org.apache.tajo.OverridableConf;
import org.apache.tajo.catalog.FunctionDesc;
import org.apache.tajo.catalog.Schema;
import org.apache.tajo.datum.Datum;
import org.apache.tajo.datum.NullDatum;
import org.apache.tajo.plan.function.FunctionInvoke;
import org.apache.tajo.plan.function.FunctionInvokeContext;
import org.apache.tajo.storage.Tuple;

import java.io.IOException;

public class GeneralFunctionEval extends FunctionEval {
  protected FunctionInvoke funcInvoke;
  @Expose protected FunctionInvokeContext invokeContext;

	public GeneralFunctionEval(OverridableConf queryContext, FunctionDesc desc, EvalNode[] givenArgs) {
		super(EvalType.FUNCTION, desc, givenArgs);
    this.invokeContext = new FunctionInvokeContext(queryContext, getParamType());
  }

  @Override
  public EvalNode bind(EvalContext evalContext, Schema schema) {
    super.bind(evalContext, schema);
    try {
      this.funcInvoke = FunctionInvoke.newInstance(funcDesc);
      if (evalContext != null) {
        if (evalContext.hasScriptEngine(this)) {
          this.invokeContext.setScriptEngine(evalContext.getScriptEngine(this));
        }

        if (evalContext.hasTimeZone()) {
          this.invokeContext.setTimeZone(evalContext.getTimeZone());
        }
      }
      this.funcInvoke.init(invokeContext);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Datum eval(Tuple tuple) {
    super.eval(tuple);
    Datum datum = funcInvoke.eval(evalParams(tuple));
    if (datum == null) {
      return NullDatum.get();
    } else {
      return datum;
    }
  }

	@Override
  public Object clone() throws CloneNotSupportedException {
    GeneralFunctionEval eval = (GeneralFunctionEval) super.clone();
    if (funcInvoke != null) {
      eval.funcInvoke = (FunctionInvoke) funcInvoke.clone();
    }
    return eval;
  }
}