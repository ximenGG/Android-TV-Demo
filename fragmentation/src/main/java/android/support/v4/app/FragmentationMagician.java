package android.support.v4.app;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class FragmentationMagician {
    private static boolean sSupportLessThan25dot4 = false;

    static {
        Field[] fields = FragmentManagerImpl.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("mAvailIndices")) {
                sSupportLessThan25dot4 = true;
                break;
            }
        }
    }

    public static boolean isExecutingActions(FragmentManager fragmentManager) {
        if (!(fragmentManager instanceof FragmentManagerImpl))
            return false;
        try {
            FragmentManagerImpl fragmentManagerImpl = (FragmentManagerImpl) fragmentManager;
            return fragmentManagerImpl.mExecutingActions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void reorderIndices(FragmentManager fragmentManager) {
        if (!sSupportLessThan25dot4) return;
        if (!(fragmentManager instanceof FragmentManagerImpl))
            return;
        try {
            FragmentManagerImpl fragmentManagerImpl = (FragmentManagerImpl) fragmentManager;
            Object object = getValue(fragmentManagerImpl, "mAvailIndices");
            if (object == null) return;

            ArrayList<Integer> arrayList = (ArrayList<Integer>) object;
            if (arrayList.size() > 1) {
                Collections.sort(arrayList, Collections.reverseOrder());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isStateSaved(FragmentManager fragmentManager) {
        if (!(fragmentManager instanceof FragmentManagerImpl))
            return false;
        try {
            FragmentManagerImpl fragmentManagerImpl = (FragmentManagerImpl) fragmentManager;
            return fragmentManagerImpl.mStateSaved;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void popBackStackAllowingStateLoss(final FragmentManager fragmentManager) {
        FragmentationMagician.hookStateSaved(fragmentManager, new Runnable() {
            @Override
            public void run() {
                fragmentManager.popBackStack();
            }
        });
    }
    public static void popBackStackImmediateAllowingStateLoss(final FragmentManager fragmentManager) {
        FragmentationMagician.hookStateSaved(fragmentManager, new Runnable() {
            @Override
            public void run() {
                fragmentManager.popBackStackImmediate();
            }
        });
    }

    public static void popBackStackAllowingStateLoss(final FragmentManager fragmentManager, final String name, final int flags) {
        FragmentationMagician.hookStateSaved(fragmentManager, new Runnable() {
            @Override
            public void run() {
                fragmentManager.popBackStack(name, flags);
            }
        });
    }
    public static void executePendingTransactionsAllowingStateLoss(final FragmentManager fragmentManager) {
        FragmentationMagician.hookStateSaved(fragmentManager, new Runnable() {
            @Override
            public void run() {
                fragmentManager.executePendingTransactions();
            }
        });
    }
    public static List<Fragment> getActiveFragments(FragmentManager fragmentManager) {
        if (!(fragmentManager instanceof FragmentManagerImpl))
            return Collections.EMPTY_LIST;
        if (sSupportLessThan25dot4) return fragmentManager.getFragments();
        try {
            FragmentManagerImpl fragmentManagerImpl = (FragmentManagerImpl) fragmentManager;
            return getActiveList(fragmentManagerImpl.mActive);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fragmentManager.getFragments();
    }
    private static List<Fragment> getActiveList(ArrayList<Fragment> active) {
        if (active == null) {
            return Collections.EMPTY_LIST;
        }
        final int count = active.size();
        ArrayList<Fragment> fragments = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            fragments.add(active.get(i));
        }
        return fragments;
    }

    private static Object getValue(Object object, String fieldName) throws Exception {
        Field field;
        Class<?> clazz = object.getClass();
        try {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
        }
        return null;
    }

    private static void hookStateSaved(FragmentManager fragmentManager, Runnable runnable) {
        if (!(fragmentManager instanceof FragmentManagerImpl)) return;
        FragmentManagerImpl fragmentManagerImpl = (FragmentManagerImpl) fragmentManager;
        if (isStateSaved(fragmentManager)) {
            fragmentManagerImpl.mStateSaved = false;
            runnable.run();
            fragmentManagerImpl.mStateSaved = true;
        } else {
            runnable.run();
        }
    }
}