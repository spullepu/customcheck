package com.mycompany.checks;
import com.puppycrawl.tools.checkstyle.api.*;

public class MethodLimitCheck extends AbstractCheck
{
  private static final int DEFAULT_MAX = 30;
  private int max = DEFAULT_MAX;

  @Override
  public int[] getDefaultTokens()
  {
    return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
  }

  @Override
  public void visitToken(DetailAST ast)
  {
    // find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
    DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

    // count the number of direct children of the OBJBLOCK
    // that are METHOD_DEFS
    int methodDefs = objBlock.getChildCount(TokenTypes.METHOD_DEF);

    // report violation if limit is reached
    if (methodDefs > this.max) {
      String message = "too many methods, only " + this.max + " are allowed";
      log(ast.getLineNo(), message);
    }
  }
  // code from above omitted for brevity
  public void setMax(int limit)
  {
    max = limit;
  }
}