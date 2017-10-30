var $ = function (id) {
	    return "string" == typeof id ? document.getElementById(id) : id;
	};
	
	function addEventHandler(oTarget, sEventType, fnHandler) {
	    if (oTarget.addEventListener) {
	        oTarget.addEventListener(sEventType, fnHandler, false);
	    } else if (oTarget.attachEvent) {
	        oTarget.attachEvent("on" + sEventType, fnHandler);
	    } else {
	        oTarget["on" + sEventType] = fnHandler;
	    }
	};
	
	function Each(arrList, fun){
	    for (var i = 0, len = arrList.length; i < len; i++) { fun(arrList[i], i); }
	};
	
	function GetOption(val, txt) {
	    var op = document.createElement("OPTION");
	    op.value = val; op.innerHTML = txt;	   
	    return op;
	};
	
	var Class = {
	  create: function() {
	    return function() {
	      this.initialize.apply(this, arguments);
	    }
	  }
	}
	
	Object.extend = function(destination, source) {
	    for (var property in source) {
	        destination[property] = source[property];
	    }
	    return destination;
	}
	
	
	var CascadeSelect = Class.create();
	CascadeSelect.prototype = {
	  //select集合，菜单对象
	  initialize: function(arrSelects, options) {
	    if(arrSelects.length <= 0) return;//菜单对象
	    
	    var oThis = this;
	    
	    this.Selects = [];//select集合
	    
	    this.Menu = [{"val":"上海","menu": [{"val":"上海市"}]},
{"val":"北京","menu": [{"val":"北京市"}]},
{"val":"重庆","menu": [{"val":"重庆市"}]},
{"val":"天津","menu": [{"val":"天津市"}]},
{"val":"安徽","menu": [{"val":"滁州市"},{"val":"合肥市"},{"val":"蚌埠市"},{"val":"芜湖市"},{"val":"淮南市"},{"val":"马鞍山"},{"val":"安庆市"},{"val":"宿州市"},{"val":"阜阳市"},{"val":"黄山市"},{"val":"淮北市"},{"val":"铜陵市"},{"val":"宣城市"},{"val":"六安市"},{"val":"巢湖市"},{"val":"贵池市"}]},
{"val":"福建","menu": [{"val":"福州市"},{"val":"厦门市"},{"val":"宁德市"},{"val":"莆田市"},{"val":"泉州市"},{"val":"晋江市"},{"val":"漳州市"},{"val":"龙岩市"},{"val":"三明市"},{"val":"南平市"}]},
{"val":"广东","menu": [{"val":"广州市"},{"val":"韶关市"},{"val":"惠州市"},{"val":"梅州市"},{"val":"汕头市"},{"val":"深圳市"},{"val":"珠海市"},{"val":"佛山市"},{"val":"肇庆市"},{"val":"湛江市"},{"val":"中山市"},{"val":"河源市"},{"val":"清远市"},{"val":"顺德市"},{"val":"云浮市"},{"val":"潮州市"},{"val":"东莞市"},{"val":"汕尾市"},{"val":"潮阳市"},{"val":"阳江市"},{"val":"揭西市"}]},
{"val":"甘肃","menu": [{"val":"临夏市"},{"val":"兰州市"},{"val":"定西市"},{"val":"平凉市"},{"val":"西峰市"},{"val":"武威市"},{"val":"张掖市"},{"val":"酒泉市"},{"val":"天水市"},{"val":"甘南州"},{"val":"白银市"}]},
{"val":"广西","menu": [{"val":"防城港"},{"val":"南宁市"},{"val":"柳州市"},{"val":"桂林市"},{"val":"梧州市"},{"val":"玉林市"},{"val":"百色市"},{"val":"钦州市"},{"val":"河池市"},{"val":"北海市"}]},
{"val":"贵州","menu": [{"val":"贵阳市"},{"val":"遵义市"},{"val":"安顺市"},{"val":"都均市"},{"val":"凯里市"},{"val":"铜仁市"},{"val":"毕节市"},{"val":"毕节市"},{"val":"兴义市"}]},
{"val":"河南","menu": [{"val":"商丘市"},{"val":"郑州市"},{"val":"安阳市"},{"val":"新乡市"},{"val":"许昌市"},{"val":"平顶山"},{"val":"信阳市"},{"val":"南阳市"},{"val":"开封市"},{"val":"洛阳市"},{"val":"焦作市"},{"val":"鹤壁市"},{"val":"濮阳市"},{"val":"周口市"},{"val":"漯河市"},{"val":"驻马店"},{"val":"三门峡"}]},
{"val":"湖北","menu": [{"val":"武汉市"},{"val":"襄城市"},{"val":"鄂州市"},{"val":"孝感市"},{"val":"黄州市"},{"val":"黄石市"},{"val":"咸宁市"},{"val":"荆沙(江陵)"},{"val":"宜昌市"},{"val":"恩施市"},{"val":"十堰市"},{"val":"随枣市"},{"val":"荆门市"},{"val":"江汉(仙桃)"}]},
{"val":"河北","menu": [{"val":"邯郸市"},{"val":"石家庄"},{"val":"保定市"},{"val":"张家口"},{"val":"承德市"},{"val":"唐山市"},{"val":"廊坊市"},{"val":"沧州市"},{"val":"衡水市"},{"val":"邢台市"},{"val":"秦皇岛"}]},
{"val":"海南","menu": [{"val":"海口市"},{"val":"三亚市"}]},
{"val":"黑龙江","menu": [{"val":"阿城市"},{"val":"哈尔滨"},{"val":"齐齐哈尔"},{"val":"牡丹江"},{"val":"佳木斯"},{"val":"绥化市"},{"val":"黑河市"},{"val":"加格达奇"},{"val":"伊春市"},{"val":"大庆市"}]},
{"val":"湖南","menu": [{"val":"岳阳市"},{"val":"长沙市"},{"val":"湘潭市"},{"val":"株州市"},{"val":"衡阳市"},{"val":"郴州市"},{"val":"常德市"},{"val":"益阳市"},{"val":"娄底市"},{"val":"邵阳市"},{"val":"吉首市"},{"val":"张家界"},{"val":"怀化市"},{"val":"永州市"}]},
{"val":"吉林","menu": [{"val":"长春市"},{"val":"吉林市"},{"val":"延吉市"},{"val":"四平市"},{"val":"通化市"},{"val":"白城市"},{"val":"辽源市"},{"val":"松原市"},{"val":"浑江市"},{"val":"珲春市"}]},
{"val":"江苏","menu": [{"val":"南京市"},{"val":"无锡市"},{"val":"镇江市"},{"val":"苏州市"},{"val":"南通市"},{"val":"扬州市"},{"val":"盐城市"},{"val":"徐州市"},{"val":"淮阴市"},{"val":"淮安市"},{"val":"连云港"},{"val":"常州市"},{"val":"泰州市"}]},
{"val":"江西","menu": [{"val":"新余市"},{"val":"南昌市"},{"val":"九江市"},{"val":"上饶市"},{"val":"临川市"},{"val":"宜春市"},{"val":"吉安市"},{"val":"赣州市"},{"val":"景德镇"},{"val":"萍乡市"},{"val":"鹰潭市"}]},
{"val":"辽宁","menu": [{"val":"沈阳市"},{"val":"铁岭市"},{"val":"大连市"},{"val":"鞍山市"},{"val":"抚顺市"},{"val":"本溪市"},{"val":"丹东市"},{"val":"锦州市"},{"val":"营口市"},{"val":"阜新市"},{"val":"辽阳市"},{"val":"朝阳市"},{"val":"盘锦市"},{"val":"葫芦岛"}]},
{"val":"内蒙古","menu": [{"val":"海拉尔"},{"val":"呼和浩特"},{"val":"包头市"},{"val":"乌海市"},{"val":"集宁市"},{"val":"通辽市"},{"val":"赤峰市"},{"val":"东胜市"},{"val":"临河市"},{"val":"锡林浩特"},{"val":"乌兰浩特"},{"val":"阿拉善左旗"}]},
{"val":"宁夏","menu": [{"val":"银川市"},{"val":"石嘴山"},{"val":"吴忠市"},{"val":"固原市"}]},
{"val":"青海","menu": [{"val":"西宁市"},{"val":"海东市"},{"val":"同仁市"},{"val":"共和市"},{"val":"玛沁市"},{"val":"玉树市"},{"val":"德令哈"}]},
{"val":"四川","menu": [{"val":"成都市"},{"val":"涪陵市"},{"val":"重庆市"},{"val":"攀枝花"},{"val":"自贡市"},{"val":"永川市"},{"val":"绵阳市"},{"val":"南充市"},{"val":"达县市"},{"val":"万县市"},{"val":"遂宁市"},{"val":"广安市"},{"val":"巴中市"},{"val":"泸州市"},{"val":"宜宾市"},{"val":"内江市"},{"val":"乐山市"},{"val":"西昌市"},{"val":"雅安市"},{"val":"雅安市"},{"val":"康定市"},{"val":"马尔康"},{"val":"德阳市"},{"val":"广元市"}]},
{"val":"山东","menu": [{"val":"菏泽市"},{"val":"济南市"},{"val":"青岛市"},{"val":"淄博市"},{"val":"德州市"},{"val":"烟台市"},{"val":"淮坊市"},{"val":"济宁市"},{"val":"泰安市"},{"val":"临沂市"}]},
{"val":"陕西","menu": [{"val":"西安市"},{"val":"咸阳市"},{"val":"延安市"},{"val":"榆林市"},{"val":"渭南市"},{"val":"商洛市"},{"val":"安康市"},{"val":"汉中市"},{"val":"宝鸡市"},{"val":"铜川市"}]},
{"val":"山西","menu": [{"val":"忻州市"},{"val":"太原市"},{"val":"大同市"},{"val":"阳泉市"},{"val":"榆次市"},{"val":"长治市"},{"val":"晋城市"},{"val":"临汾市"},{"val":"离石市"},{"val":"运城市"}]},
{"val":"新疆","menu": [{"val":"乌鲁木齐"}]},
{"val":"西藏","menu": [{"val":"拉萨市"},{"val":"日喀则"},{"val":"山南市"}]},
{"val":"云南","menu": [{"val":"昭通市"},{"val":"昆明市"},{"val":"大理市"},{"val":"个旧市"},{"val":"曲靖市"},{"val":"保山市"},{"val":"文山市"},{"val":"玉溪市"},{"val":"楚雄市"},{"val":"思茅市"},{"val":"景洪市"},{"val":"潞西市"},{"val":"东川市"},{"val":"临沧市"},{"val":"六库市"},{"val":"中甸市"},{"val":"丽江市"}]},
{"val":"浙江","menu": [{"val":"衢州市"},{"val":"杭州市"},{"val":"湖州市"},{"val":"嘉兴市"},{"val":"宁波市"},{"val":"绍兴市"},{"val":"台州市"},{"val":"温州市"},{"val":"丽水市"},{"val":"金华市"},{"val":"舟山市"}]},
{"val":"澳门","menu": [{"val":"澳门"}]},
{"val":"台湾","menu": [{"val":"台湾"}]},
{"val":"香港","menu": [{"val":"香港"}]}
    	];
	    
	    this.SetOptions(options);
	    
	    this.Default = this.options.Default || [];
	    
	    this.ShowEmpty = !!this.options.ShowEmpty;
	    this.EmptyText = this.options.EmptyText.toString();
	    
	    //设置Selects集合和change事件
	    Each(arrSelects, function(o, i){
	        addEventHandler((oThis.Selects[i] = $(o)), "change", function(){ oThis.Set(i); });
	    });
	    
	    this.ReSet();
	  },
	  //设置默认属性
	  SetOptions: function(options) {
	    this.options = {//默认值
	        Default:    [],//默认值(按顺序)
	        ShowEmpty:    false,//是否显示空值(位于第一个)
	        EmptyText:    "请选择"//空值显示文本(ShowEmpty为true时有效)
	    };
	    Object.extend(this.options, options || {});
	  },
	  //初始化select
	  ReSet: function() {
	  
	    this.SetSelect(this.Selects[0], this.Menu, this.Default.shift());
	    this.Set(0);
	  },
	  //全部select设置
	  Set: function(index) {
	    var menu = this.Menu
	    
	    //第一个select不需要处理所以从1开始
	    for(var i=1, len = this.Selects.length; i < len; i++){
	        if(menu.length > 0){
	            //获取菜单
	            var value = this.Selects[i-1].value;
	            if(value!=""){
	                Each(menu, function(o){ if(o.val == value){ menu = o.menu || []; } });
	            } else { menu = []; }
	            
	            //设置菜单
	            if(i > index){ this.SetSelect(this.Selects[i], menu, this.Default.shift()); }
	        } else {
	            //没有数据
	            this.SetSelect(this.Selects[i], [], "");
	        }
	    }
	    //清空默认值
	    this.Default.length = 0;
	  },
	  //select设置
	  SetSelect: function(oSel, menu, value) {
	    oSel.options.length = 0; oSel.disabled = false;
	    if(this.ShowEmpty){ oSel.appendChild(GetOption("", this.EmptyText)); }
	    if(menu.length <= 0){ oSel.disabled = true; return; }
	    
	    Each(menu, function(o){
	        var op = GetOption(o.val, o.txt ? o.txt : o.val); op.selected = (value == op.value);
	        oSel.appendChild(op);
	    });    
	  },
	  //添加菜单
	  Add: function(menu) {
	    this.Menu[this.Menu.length] = menu;
	    this.ReSet();
	  },
	  //删除菜单
	  Delete: function(index) {
	    if(index < 0 || index >= this.Menu.length) return;
	    for(var i = index, len = this.Menu.length - 1; i < len; i++){ this.Menu[i] = this.Menu[i + 1]; }
	    this.Menu.pop()
	    this.ReSet();
	  }
	};
	
	