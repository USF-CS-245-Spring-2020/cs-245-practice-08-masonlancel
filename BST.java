public class BST<T extends Comparable>{
	
	BSTNode root;

	public class BSTNode<T>{
		T value;
		Comparable key;
		BSTNode left;
		BSTNode right;

		public BSTNode(Comparable newkey){
			key = newkey;
		}
	}

	public boolean find(Comparable key){
		return find(root,key);
	}

	protected boolean find(BSTNode node, Comparable key){
		if(node == null)
			return false;
		if(node.key.compareTo(key)==0)
			return true;
		else if(node.key.compareTo(key)<0)
			return(find(node.left,key));
		else
			return(find(node.right,key));
	}

	public void insert(Comparable key){
		root = insert(root, key);
		//root.key = key;
	}

	protected BSTNode insert(BSTNode node, Comparable key){
		if(node == null)
			return new BSTNode(key);
		if(node.key.compareTo(key)<0)
			node.left = insert(node.left, key);
		else
			node.right = insert(node.right, key);
		
		return node;
	}

	public void print(){
		inOrderTraversal(root);
	}

	protected void inOrderTraversal(BSTNode node){
		if(node!=null){
			inOrderTraversal(node.left);
			System.out.println(node.key);
			inOrderTraversal(node.right);
		}
	}

	public void delete(Comparable key){
		root = delete(root, key);
	}

	protected BSTNode delete(BSTNode node, Comparable key){
		if(node == null)
			return null;
		if(node.key.compareTo(key)>0){
			node.right = delete(node.right, key);
			return node;
		}
		else if(node.key.compareTo(key)<0){
			node.left = delete(node.left, key);
			return node;
		}
		else{
			if(node.left == null)
				return node.right;
			else if(node.right == null)
				return node.left;
			else{
				if(node.right.left == null){
					node.value = node.right.value;
					node.right = node.right.right;
				}
				else{
					node.value = removeSmallest(node.right);
				}
				return node;
			}
		}
	}

	public BSTNode removeSmallest(BSTNode node){
		if(node.left.left == null){
			BSTNode smallest = null;
			smallest.value = node.left.value;
			node.left = node.left.right;
			return smallest;
		}
		else
			return removeSmallest(node.left);
	}
}