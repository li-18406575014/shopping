package com.hyxy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hyxy.entity.Jieshao;
import com.hyxy.es.ProductDao;
import com.hyxy.service.JieshaoService;
import com.hyxy.util.PageUtils;
import com.hyxy.util.Upload;
@Controller
@RequestMapping("ItemAddController")
public class ItemAddController {

	@Resource
	private AmqpTemplate amqpTemplate;
	@Resource
	private JieshaoService JieshaoService;

	// 点击跳转到商品新增页面
	@RequestMapping("skip")
	public String skip(Map<String, Object> map2,String currentPage, String pageSize) {
		List<Jieshao> list = JieshaoService.selectAll();
		Integer count = list.size();

		// 调用分页工具类
		PageUtils pageUtils = new PageUtils(currentPage, count, pageSize);
		// 从分页工具类中得到开始记录数
		Integer begin = pageUtils.getPageRecord();
		// 从分页工具类中得到每页显示记录数
		Integer number = pageUtils.getPageSize();
		// 从分页工具类中得到前台页面分页模型（传入页面使用的html代码）
		String page = pageUtils.getPage();
		// 实现分页
		List<Jieshao> list2 = JieshaoService.selectFenYe(begin, number);

		map2.put("ban", list2);
		map2.put("page", page);
		map2.put("number", number);
//		map2.put("ban", list);
		return "goods-add";
	}

	// 点击新增跳转的页面
	@RequestMapping("goToSkip")
	public String goToSkip() {
		return "good-add2";
	}

	// 实现图片的预览上传
	@RequestMapping("imgPreview")
	@ResponseBody
	public List<Map<String, String>> imgPreview(Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "banner", required = false) MultipartFile[] img) {
		Upload upload = new Upload();
		List<Map<String, String>> list = upload.uploadfile(request, img);
		Map<String, Object> map3 = new HashMap<>();
		map3.put("list", list);
//		for (Map<String, String> map2 : list) {
//			String path = map2.get("path");
//			String savePath = map2.get("savePath");
//		}
		return list;
	}

	// 将添加的数据传递到队列中
	@RequestMapping("addIncreased")
	@ResponseBody
	public Map<String, String> addIncreased(@RequestParam Map<String, Object> map) {
		String banner = (String) map.get("banner");
//		String banner1 = map.get("banner1");
		String path = banner;
		map.put("path", path);
		amqpTemplate.convertAndSend("fanout_exchange", "inster", map);
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("message", "成功");
		return map1;
	}
	//点击编辑按id进行查询
	@RequestMapping("selectId")
	public String selectId(Map<String, Jieshao> map,Integer id) {
		Jieshao jieshao = JieshaoService.selectId(id);
		map.put("banner", jieshao);
		return "good-add2";
	}
	@RequestMapping("updataId")
	public String updataId(@RequestParam Map<String, Object> map) {
		if (map.get("imgname")!=map.get("banner")) {
			map.put("imgname", map.get("banner"));
		}
		Integer id = Integer.parseInt((String) map.get("id"));
		map.put("id", id);
		JieshaoService.updataJS(map);
		return "forward:/ItemAddController/skip";
	}
	//删除
	@RequestMapping("delectId")
	public String delectId(Integer id) {
		JieshaoService.delectId(id);
		return "forward:/ItemAddController/skip";
	}
	
}
