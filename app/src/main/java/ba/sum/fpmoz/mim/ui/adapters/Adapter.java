package ba.sum.fpmoz.mim.ui.adapters;

import android.view.View;

public class Adapter {
    public interface ClickListener {
        public void OnClickListener (View v, int position);
        public void OnLongClickListener (View v, int position);
    }
}
