package ias.demo.wechat.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ias.common.utils.encrypt.aes.AesAuthUtil;
import com.ias.common.utils.web.UrlEscape;

@Controller
public class ScanController {
	
	/**
	 * 扫码后微信公众号跳转过来
	 * @author jiuzhou.hu
	 * @date 2018年5月8日 下午6:38:28
	 * @param model
	 * @param encrypt	关注微信后获取到的加密数据
	 * 					如：7dHVBwNkKt/6icmRHEATSSlGJBVAm9MgJldL7uEbrizHA/u0W0ZHBuF1RWmqMIW9ixbDwevdJKW0giqV+Kkk+kIG9gE3MGkzEi51GL9ZHJ+VXJHPvstCQhE8o4wVyKw6I0vjJxGbMots4AEDUPlV5FzmiF4CTzPpATReRrSQsXCwqU1b4OUmBva1k4HyBT3cyFHRe6kGATlFJ+tmigTASd943762pHFY3a+HxpMhUBzSPS1b1aOl3YVqLskXgc8aPWkGWz/RwK5tBwefvG45F+Od+iFnTUJU4k171jpAKD8qNnCMCl17Hdl4U6DDeJpM2XeiIffnL/OXswCZnjN3ZPDJTSTffC7j9SuchWHDBbQ7TEiDFnJmrGyX3GbBO+wv/yRK4aZ2BiD+b+oqnRyYo9XzZrC5GwrMBOXpt25DIWY58l9RQx3e5wxTMLsxndOng/RyDPSHzkcc1zFV+sn/I7jY1sMVYIFFj8+TkDeDIeRnV7fMCHMCKcBbkJAXbr5lzZ1QdALdMqqK8aeWOMTmZeDB9/ReBBtzFt07P9PKfZhGNTT3vGzGFKvqy7oauIXnrqEGi6whYo2SqXuTfie9NHGZJB9iqhw+JUAxixzsvk+3OioNZaijlGZCx6pAz/5wQZuvW9SDLjQWbEmEQxBsb65JVu6HO/NUZDFH9M/Bcbd1wdZ3i2VZe2kMwCbsPwNVRmYOBAJ4tY6B3YOLioyFKQAQDrLZ2+5FdGObUX6VSWGMgw+lDAp3PA1haS97hyBZ
	 * @see ias.test.GenerateUrlTest
	 * @return
	 */
	@RequestMapping(value="scan")
	public String scan(Model model, String encrypt) {
		//解密数据为json格式
		String json = AesAuthUtil.decrypt(UrlEscape.unescape(encrypt));
		System.out.println(json);
		
		model.addAttribute("encrypt", encrypt);
		return "scan";
	}
}
