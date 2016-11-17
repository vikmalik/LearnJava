package com.learnjava.jna;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.win32.StdCallLibrary;

public class GetNativeWindow {
	public static void main(String[] args) {
		final List<WindowInfo> inflList = new ArrayList<WindowInfo>();
		final List<Integer> order = new ArrayList<Integer>();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		HWND foregroundWindowHandle = User32.INSTANCE.GetForegroundWindow();
		WindowInfo windowInfo = getWindowsDetails(foregroundWindowHandle, null);
		System.out.println("Foreground Window = " + windowInfo);
		
//		while (foregroundWindowHandle != 0) {
//			order.add(foregroundWindowHandle);
//			foregroundWindowHandle = User32.INSTANCE.GetWindow(foregroundWindowHandle, User32.GW_HWNDNEXT);
//		}
		
//		User32.INSTANCE.EnumWindows( new WNDENUMPROC() {
//			
//			@Override
//			public boolean callback(HWND hWnd, Pointer pointer) {
//				WindowInfo windowInfo = getWindowsDetails(hWnd, pointer);
//				if(windowInfo != null){
//					inflList.add(windowInfo);
//				}
//				return true;
//			}
//		}, new Pointer(0));
		
		Collections.sort(inflList, new Comparator<WindowInfo>() {
			public int compare(WindowInfo o1, WindowInfo o2) {
				return order.indexOf(o1.HWND) - order.indexOf(o2.HWND);
			}
		});
		for (WindowInfo w : inflList) {
			System.out.println(w);
		}
	}
	
	public static WindowInfo getWindowsDetails(HWND hWnd, Pointer pointer){
		WindowInfo windowInfo = null;
		
		boolean isWindowVisible = User32.INSTANCE.IsWindowVisible(hWnd);
		
		if (User32.INSTANCE.IsWindowVisible(hWnd)) {
			RECT rect = new RECT();
			User32.INSTANCE.GetWindowRect(hWnd, rect);
			if (rect.left > -32000) { // minimized
				char[] buffer = new char[1024];
				User32.INSTANCE.GetWindowText(hWnd, buffer, buffer.length);
				String title = new String(buffer);
				windowInfo = new WindowInfo(hWnd, rect, title, isWindowVisible);
			}
		}
		return windowInfo;
	}

	public static interface WndEnumProc extends StdCallLibrary.StdCallCallback {
		boolean callback(int HWND, int lParam);
	}

//	public static interface User32 extends StdCallLibrary {
//		final User32 instance = (User32) Native.loadLibrary("user32",
//				User32.class);
//
//		boolean EnumWindows(WndEnumProc wndenumproc, int lParam);
//
//		boolean IsWindowVisible(int HWND);
//
//		int GetWindowRect(int HWND, RECT r);
//
//		void GetWindowTextA(int HWND, byte[] buffer, int buflen);
//
//		int GetTopWindow(int HWND);
//
//		int GetWindow(int HWND, int flag);
//
//		final int GW_HWNDNEXT = 2;
//	}

//	public static class RECT extends Structure {
//		public int left, top, right, bottom;
//
//		@Override
//		protected List<String> getFieldOrder() {
//			List<String> fieldOrder = new ArrayList<String>();
//			fieldOrder.add("left");
//			fieldOrder.add("top");
//			fieldOrder.add("right");
//			fieldOrder.add("bottom");
//			
//			return fieldOrder;
//		}
//	}

	public static class WindowInfo {
		HWND HWND;
		RECT rect;
		String title;
		boolean isVisible;

		public WindowInfo(HWND hWnd2, RECT rect, String title, boolean isVisible) {
			this.HWND = hWnd2;
			this.rect = rect;
			this.title = title;
			this.isVisible = isVisible;
		}

		public String toString() {
			return String.format("(%d,%d)-(%d,%d) : \"%s\" : %s", rect.left,
					rect.top, rect.right, rect.bottom, title, isVisible);
		}
	}
}
