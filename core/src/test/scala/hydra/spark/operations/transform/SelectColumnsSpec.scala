/*
 * Copyright (C) 2017 Pluralsight, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hydra.spark.operations.transform

import hydra.spark.api.Invalid
import hydra.spark.testutils.{SharedSparkContext, StaticJsonSource}
import org.apache.spark.sql.SQLContext
import org.scalatest.{FunSpecLike, Matchers}

/**
  * Created by alexsilva on 8/12/16.
  */
class SelectColumnsSpec extends Matchers with FunSpecLike with SharedSparkContext {

  describe("It should select column") {
    it("selects one column") {
      val sqlContext = new SQLContext(sc)
      val df = SelectColumns(Seq("msg-no")).transform(StaticJsonSource.createDF(sqlContext))
      df.columns shouldBe Array("msg-no")
    }

    it("selects multiple column") {
      val sqlContext = new SQLContext(sc)
      val df = SelectColumns(Seq("msg-no","data")).transform(StaticJsonSource.createDF(sqlContext))
      df.columns shouldBe Array("msg-no","data")
    }

    it("validates") {
      SelectColumns(Seq.empty).validate shouldBe an[Invalid]
    }
  }
}
