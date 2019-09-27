# Note

	总笔记目录

# Index

1. [图像学](#image)
2. [数学](#math)
3. [机械学习](#ml)

## Math

	狄拉克分布 dirac distribution
	https://www.khanacademy.org/math/differential-equations/laplace-transform/properties-of-laplace-transform/v/dirac-delta-function

![](img/math-dirac-0.png)

	表示一个很突然的值，积分取值永远为1.
	

## Image

	和机械学习没有直接关系的图像的相关理论内容

### Image 光照和颜色

	图像上最后产生的颜色受3个东西影响：
	1.照射光，(光的颜色，强度
	2.物体对光的反射性质，（本身的颜色
	3.摄像设备的颜色特性

	因为图像上只有最后的一个颜色值，所以当需要还原物体真正的颜色时，是一个病态问题。

## Ml

### K-NN

	简单的一种分类算法，也就是通过判断（新值的）K个最近样本中占最多数的类型是什么，
	来决定新值的类型。
	基于“相似的样本会聚集在一起”这个假设，K值是个超参，直接影响结果。

![](img/ml-knn.jpg)

