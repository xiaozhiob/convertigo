<script>
	export let items = [];
	export let index = 0;
	export let grabClass = '';
	export let dragging = false;

	let draggedIndex = null;
	let indexesRect = [];

	function handleDragStart(index) {
		draggedIndex = index;
		dragging = true;
		indexesRect = getIndexesRect();
	}

	function handleDragEnter(index) {
		if (!dragging || index === draggedIndex) return;
		let copy = [...items];
		const draggedItem = items[draggedIndex];
		copy.splice(draggedIndex, 1);
		copy.splice(index, 0, draggedItem);
		draggedIndex = index;
		items = copy;
	}

	function handleDragEnd() {
		draggedIndex = null;
		dragging = false;
	}

	function getIndexesRect() {
		const elements = Array.from(document.querySelectorAll('.movable'));
		return elements.map((element) => element.getBoundingClientRect());
	}

	function handleMouseDown(event, index) {
		if (grabClass.length > 0 && !event.target.closest(`.${grabClass}`)) return;
		event.preventDefault();

		handleDragStart(index);

		const handleMouseMove = (event) => {
			const clientX = event.touches ? event.touches[0].clientX : event.clientX;
			const clientY = event.touches ? event.touches[0].clientY : event.clientY;
			const targetIndex = getTargetIndex(clientX, clientY);
			handleDragEnter(targetIndex);
		};

		const handleMouseUp = () => {
			handleDragEnd();
			window.removeEventListener('mousemove', handleMouseMove);
			window.removeEventListener('mouseup', handleMouseUp);
			window.removeEventListener('touchmove', handleMouseMove);
			window.removeEventListener('touchend', handleMouseUp);
		};

		window.addEventListener('mousemove', handleMouseMove);
		window.addEventListener('mouseup', handleMouseUp);
		window.addEventListener('touchmove', handleMouseMove);
		window.addEventListener('touchend', handleMouseUp);
	}

	function getTargetIndex(clientX, clientY) {
		const elements = Array.from(document.querySelectorAll('.movable'));
		let targetIndex = draggedIndex;
		/*elements.forEach((element, index) => {
			const rect = element.getBoundingClientRect();
			if (
				clientX >= rect.left &&
				clientX <= rect.right &&
				clientY >= rect.top &&
				clientY <= rect.bottom
			) {
				targetIndex = index;
			}
		});*/
		indexesRect.forEach((rect, index) => {
			if (
				clientX >= rect.left &&
				clientX <= rect.right &&
				clientY >= rect.top &&
				clientY <= rect.bottom
			) {
				targetIndex = index;
			}
		});
		return targetIndex;
	}
</script>

<!-- svelte-ignore a11y-no-static-element-interactions -->
<div
	class="movable"
	on:mousedown={(e) => handleMouseDown(e, index)}
	on:touchstart={(e) => handleMouseDown(e, index)}
	{...$$restProps}
>
	<slot />
</div>
