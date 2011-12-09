package com.gmail.samidarehetima;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.font.SystemFont;

/**
 * GTGE用に作成したボタン。Spriteの一種である。
 * 「とりあえず作った」程度の物。
 * 
 * [memo]GTGE-GUIを使うとボタンは作らなくてもいい。
 * ただし，GTGE-GUIは使い勝手が悪そう。
 * ラベルなど，GTGE-GUIの他の機能も必要そうなら使ってもいい。
 * 
 */
public class OrgButton extends Sprite
{
    private static final long serialVersionUID = -4054972507274515971L;
    private final int         ButtonWidth      = 128;
    private final int         ButtonHeight     = 32;
    private final Color       ButtonColor      = Color.DARK_GRAY;

    private BaseInput         bsInput;
    private ButtonListener    bl               = null;
    private String            text             = "button";

    public OrgButton(BaseInput bsInput, String text, double x, double y)
    {
        super(x, y);
        this.bsInput = bsInput;
        this.text = text;
    }

    /*[memo]awtのButtonは複数のActionListenerを登録できる*/
    public void setActionListener(ButtonListener bl)
    {
        this.bl = bl;
    }

    @Override
    public void update(long elapsedTime)
    {
        super.update(elapsedTime);

        //ボタンが左クリックされた時の処理は，他のクラスで定義する。
        if (isMouseLeftButtonPressed())
        {
            if (this.bl != null)
            {
                this.bl.actionPerformed();
            }
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        super.render(g);

        createButtonRect(g, true);
        createText(g);

        //カーソルが重なったら，ボタンを枠で囲む
        if (isMouseCursorOverlaped())
        {
            createButtonOutsideRect(g);
        }
    }

    private void createText(Graphics2D g)
    {
        final int fontSize = 20;
        SystemFont font = new SystemFont(new Font("Monospace", Font.BOLD,
                fontSize));
        font.setColor(Color.WHITE);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        font.drawString(g, this.text, (int) (super.getX() + fontSize),
                (int) (super.getY() + (this.ButtonHeight - fontSize) / 2));
    }

    private void createButtonOutsideRect(Graphics2D g)
    {
        Color color = g.getColor();
        g.setColor(this.ButtonColor);
        g.drawRect((int) super.getX(), (int) super.getY(), this.ButtonWidth,
                this.ButtonHeight);
        g.setColor(color);
    }

    private void createButtonRect(Graphics2D g, boolean raised)
    {
        Color oldColor = g.getColor();
        g.setColor(this.ButtonColor);
        g.fill3DRect((int) super.getX(), (int) super.getY(), this.ButtonWidth,
                this.ButtonHeight, raised);
        g.setColor(oldColor);
    }

    //===== ここから下は，マウスに関するコード ===================
    private boolean isMouseLeftButtonPressed()
    {
        return isMouseCursorOverlaped()
                && this.bsInput.isMousePressed(MouseEvent.BUTTON1);
    }

    private boolean isMouseCursorOverlaped()
    {
        final double cursorX = this.bsInput.getMouseX();
        final double cursorY = this.bsInput.getMouseY();

        final double buttonLeft = super.getX();
        final double buttonRight = super.getX() + this.ButtonWidth;
        final double buttonTop = super.getY();
        final double buttonBottom = super.getY() + this.ButtonHeight;

        final boolean isVertical = buttonLeft < cursorX
                && cursorX < buttonRight;
        final boolean isHorizontal = cursorY < buttonBottom
                && buttonTop < cursorY;
        //< 座標系は左上が原点なので，このようになる。

        if (isVertical && isHorizontal)
        {
            return true;
        }
        return false;
    }
}
