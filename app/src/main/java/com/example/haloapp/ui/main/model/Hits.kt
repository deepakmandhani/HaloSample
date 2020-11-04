import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Hits (

	@SerializedName("created_at") val created_at : String,
	@SerializedName("title") val title : String,
	@SerializedName("url") val url : String,
	@SerializedName("author") val author : String,
	@SerializedName("points") val points : Int,
	@SerializedName("story_text") val story_text : String,
	@SerializedName("comment_text") val comment_text : String,
	@SerializedName("num_comments") val num_comments : Int,
	@SerializedName("story_id") val story_id : String,
	@SerializedName("story_title") val story_title : String,
	@SerializedName("story_url") val story_url : String,
	@SerializedName("parent_id") val parent_id : String,
	@SerializedName("created_at_i") val created_at_i : Int,
	@SerializedName("relevancy_score") val relevancy_score : Int,
	@SerializedName("_tags") val _tags : List<String>,
	@SerializedName("objectID") val objectID : Int,
	@SerializedName("_highlightResult") val _highlightResult : _highlightResult
)