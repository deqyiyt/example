package ias.test;

import org.junit.Test;

import com.ias.common.utils.encrypt.aes.AesAuthUtil;

public class GenerateUrlTest {
	
	/**
	 * 生成微信公众号跳转的URL
	 * @author jiuzhou.hu
	 * @date 2018年5月8日 下午6:29:42
	 */
	@Test
	public void generateUrlTest() {
		//需要接受重定向的URL
		String callbackUrl = "https://wechat.fuyitianjian.net/demo/scan";
		
		//需要透传给云医管的数据，预留
		String params = "{}";
		//将参数格式化为json
		String json = "{\"url\":\""+callbackUrl+"\",\"params\":"+params+"}";
		System.out.println(json);
		
		//生成所需要的加密串
		String encrypt = AesAuthUtil.encrypt(json);
		System.out.println(encrypt);
		
		//生成最终菜单/二维码包含的URL地址
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8008b98912475a9d&redirect_uri=https://wechat.fuyitianjian.net/wechat/auth?encryptStr=%s&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		System.out.println(String.format(url, encrypt));
	}
	
	/**
	 * 解密字符串
	 * @author jiuzhou.hu
	 * @date 2018年5月8日 下午7:34:54
	 */
	@Test
	public void decryptTest() {
		String decrypt = "7dHVBwNkKt/6icmRHEATSSlGJBVAm9MgJldL7uEbrizHA/u0W0ZHBuF1RWmqMIW9ixbDwevdJKW0giqV+Kkk+kIG9gE3MGkzEi51GL9ZHJ+VXJHPvstCQhE8o4wVyKw6I0vjJxGbMots4AEDUPlV5FzmiF4CTzPpATReRrSQsXCwqU1b4OUmBva1k4HyBT3cyFHRe6kGATlFJ+tmigTASd943762pHFY3a+HxpMhUBzSPS1b1aOl3YVqLskXgc8aPWkGWz/RwK5tBwefvG45F+Od+iFnTUJU4k171jpAKD8qNnCMCl17Hdl4U6DDeJpM2XeiIffnL/OXswCZnjN3ZPDJTSTffC7j9SuchWHDBbQ7TEiDFnJmrGyX3GbBO+wv/yRK4aZ2BiD+b+oqnRyYo9XzZrC5GwrMBOXpt25DIWY58l9RQx3e5wxTMLsxndOng/RyDPSHzkcc1zFV+sn/I7jY1sMVYIFFj8+TkDeDIeRnV7fMCHMCKcBbkJAXbr5lzZ1QdALdMqqK8aeWOMTmZeDB9/ReBBtzFt07P9PKfZhGNTT3vGzGFKvqy7oauIXnrqEGi6whYo2SqXuTfie9NHGZJB9iqhw+JUAxixzsvk+3OioNZaijlGZCx6pAz/5wQZuvW9SDLjQWbEmEQxBsb65JVu6HO/NUZDFH9M/Bcbd1wdZ3i2VZe2kMwCbsPwNVRmYOBAJ4tY6B3YOLioyFKQAQDrLZ2+5FdGObUX6VSWGMgw+lDAp3PA1haS97hyBZ";
		System.out.println(AesAuthUtil.decrypt(decrypt));
	}
}
